package com.example.employeeservice.service.impl;

import com.employee.core.dto.RemindTask;
import com.example.employeeservice.service.KafkaSender;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaSenderImpl implements KafkaSender {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendTaskRemind(RemindTask remind) {
        var record = new ProducerRecord<String, Object>(
                "remind_task_topic",
                String.valueOf(remind.getTask().getTaskId()),
                remind
        );
        record.headers().add("messageId", UUID.randomUUID().toString().getBytes());
        try {
            kafkaTemplate.send(record).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
