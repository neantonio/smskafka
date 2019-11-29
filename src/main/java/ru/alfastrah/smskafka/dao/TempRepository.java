package ru.alfastrah.smskafka.dao;

import org.springframework.stereotype.Component;
import ru.alfastrah.smskafka.entity.SmsRecord;

import java.util.Date;
import java.util.List;

@Component
public class TempRepository implements SmsKafkaRepository {
    public SmsRecord saveSmsRecord(SmsRecord smsRecord) {
        return new SmsRecord();
    }

    public List<SmsRecord> getSmsRecordsByPhoneNumberBetweenDate(String phoneNumber, Date begin, Date end) {
        return null;
    }
}
