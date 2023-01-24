package com.usermanagement.usermanagementproducer.producers;

import com.usermanagement.usermanagementproducer.events.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class UserEventProducer {

    @Autowired
   KafkaTemplate<String, UserEvent> kafkaTemplate;


    public void sendUserEvent(UserEvent userEvent){
        ProducerRecord<String, UserEvent> producerRecord = new ProducerRecord<>("user-event-topic"
                                                                            ,userEvent.getUserName().toString()
                                                                            ,userEvent);

        CompletableFuture<SendResult<String, UserEvent>> result = kafkaTemplate.send(producerRecord);
        result.whenComplete((r,e) ->{
            ProducerRecord<String, UserEvent> producerRecord1 = r.getProducerRecord();
            RecordMetadata recordMetadata = r.getRecordMetadata();
            log.info("user sent to topic {} with key {} and payload {}"
                    ,recordMetadata.topic()
                    ,producerRecord1.key()
                    ,producerRecord1.value()
                    );
        });
    }


}
