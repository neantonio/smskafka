package ru.alfastrah.smskafka;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"ru.alfastrah.smskafka", "ru.alfastrah.smskafka.config"})
public class SmsKafkaApplication  {

    public static void main(String[] args) {
        SpringApplication.run(SmsKafkaApplication.class, args);
    }


}
