package ru.alfastrah.smskafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.alfastrah.smskafka.dao.SmsKafkaRepository;
import ru.alfastrah.smskafka.entity.DeliveryStatus;
import ru.alfastrah.smskafka.entity.SmsRecord;
import ru.alfastrah.smskafka.model.AfterSmsSend;
import ru.alfastrah.smskafka.model.BeforeSmsSend;

import java.util.Calendar;
import java.util.UUID;

@Component
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsKafkaRepository smsKafkaRepository;

    @Autowired
    private SoapCallerImpl soapCaller;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${sendTopic}")
    private String sendTopic;

    @Value("${sms.gateway}")
    private String gateway;

    @Value("${sms.gateway.login}")
    private String login;

    @Value("${sms.gateway.password}")
    private String pass;

    @KafkaListener(groupId = "server.broadcast", topics = "demoCallback")
    public void processAfterSend(String json){
        AfterSmsSend afterSmsSend;
        SmsRecord smsRecord = smsKafkaRepository.getSmsRecordById(UUID.fromString(afterSmsSend.getEsbId()));
        if(smsRecord != null){
            smsRecord.setStatus(DeliveryStatus.fromId());
            smsRecord.setDoneDate();
            executeCallback(smsRecord.getCallback());
        }
    }

    private void executeCallback(String callback) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<String> restResponse = restTemplate.exchange(
                UriComponentsBuilder.fromHttpUrl(callback).build().toUriString(),
                HttpMethod.GET,
                new HttpEntity<String>(""),
                String.class);

    }

    @KafkaListener(groupId = "server.broadcast", topics = "demoSMS")
    public void processBefore(String json){

    }

    public String sendSms(String phone, String text, String callback){
        SmsRecord smsRecord = new SmsRecord(phone,text, Calendar.getInstance().getTime(),null);
        smsRecord.setCallback(callback);
        smsRecord = smsKafkaRepository.saveSmsRecord(smsRecord);
        String esbId = soapCaller.sendSMSNotification(phone,text);

        smsRecord.setExtId(esbId);
        smsRecord = smsKafkaRepository.saveSmsRecord(smsRecord);

        return esbId;
    }

}
