package ru.alfastrah.smskafka.service;

public interface SmsService {

    String sendSms(String phone, String text, String callback);
}
