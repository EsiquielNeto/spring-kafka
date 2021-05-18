package com.project.study_kafka_consumer.consumer;

import com.project.study_kafka_consumer.dto.CarDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CarConsumer {
    private static final Logger logger = LoggerFactory.getLogger(CarConsumer.class);

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.group_id}")
    private String groupId;

    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.group_id}", containerFactory = "kafkaListenerContainerFactory")
    public void listenTopicCar(ConsumerRecord<String, CarDTO> record) {
        logger.info("Received Message");
        logger.info("Partition " + record.partition());
        logger.info("Message " + record.value());
    }
}
