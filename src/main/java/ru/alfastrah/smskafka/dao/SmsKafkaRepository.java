package ru.alfastrah.smskafka.dao;

import ru.alfastrah.smskafka.entity.SmsRecord;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface SmsKafkaRepository {

    SmsRecord saveSmsRecord(SmsRecord smsRecord);

    SmsRecord getSmsRecordById(UUID id);

    List<SmsRecord> getSmsRecordsByPhoneNumberBetweenDate(String phoneNumber, Date begin, Date end);
}
