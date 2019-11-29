package ru.alfastrah.smskafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.alfastrah.smskafka.model.AfterSmsSend;
import ru.alfastrah.smskafka.model.BeforeSmsSend;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.server}")
    private String kafkaServer;

    @Value("${kafka.producer.id}")
    private String kafkaProducerId;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerId);
        return props;
    }

    @Bean
    public ProducerFactory<Long, BeforeSmsSend> beforeSendProducerFactory() {
        return new DefaultKafkaProducerFactory<Long, BeforeSmsSend>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, BeforeSmsSend> beforeSendTemplate() {
        KafkaTemplate<Long, BeforeSmsSend> template = new KafkaTemplate<Long, BeforeSmsSend>(beforeSendProducerFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }

    @Bean
    public ProducerFactory<Long, AfterSmsSend> afterSendProducerFactory() {
        return new DefaultKafkaProducerFactory<Long, AfterSmsSend>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Long, AfterSmsSend> afterSendTemplate() {
        KafkaTemplate<Long, AfterSmsSend> template = new KafkaTemplate<Long, AfterSmsSend>(afterSendProducerFactory());
        template.setMessageConverter(new StringJsonMessageConverter());
        return template;
    }
}
