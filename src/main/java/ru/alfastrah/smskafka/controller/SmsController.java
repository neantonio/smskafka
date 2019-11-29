package ru.alfastrah.smskafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alfastrah.smskafka.dao.SmsKafkaRepository;
import ru.alfastrah.smskafka.entity.SmsRecord;
import ru.alfastrah.smskafka.service.SmsService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sms")
public class SmsController {

    private String dateFormat;

    @Autowired
    private SmsService smsService;

    @Autowired
    private SmsKafkaRepository smsKafkaRepository;

    @GetMapping("/getSmsRecordsByPhoneAndDate")
    public List<SmsRecord> getSmsWithStatusByPhone(@RequestParam String phone,
                                                   @RequestParam String beginDate,
                                                   @RequestParam String endDate,
                                                   @RequestParam String callback){
        Date begin = parseDate(beginDate);
        Date end = parseDate(endDate);
        return smsKafkaRepository.getSmsRecordsByPhoneNumberBetweenDate(phone, begin, end);
    }

    @GetMapping("/getSmsRecordsById")
    public SmsRecord getSmsWithStatusByPhone(@RequestParam String id){
        return smsKafkaRepository.getSmsRecordById(UUID.fromString(id));
    }

    @GetMapping("/getSmsRecords")
    public String sendSms(@RequestParam String phone, @RequestParam String text, @RequestParam String callback){
        return smsService.sendSms(phone, text, callback);
    }

    private Date parseDate(String beginDate) {
        return null;
    }
}
