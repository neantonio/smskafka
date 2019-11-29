package ru.alfastrah.smskafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.alfastrah.smskafka.model.AfterSmsSend;
import ru.alfastrah.smskafka.model.BeforeSmsSend;

@Component
public class TestSmsKafkaService {

    @Autowired
    private KafkaTemplate<Long, BeforeSmsSend> beforeSendTemplate;

    @Autowired
    private KafkaTemplate<Long, AfterSmsSend> afterSendTemplate;

    public void putBeforeMessageToKafka(){

    }

    public void putAfterMessageToKafka(){

    }
}
