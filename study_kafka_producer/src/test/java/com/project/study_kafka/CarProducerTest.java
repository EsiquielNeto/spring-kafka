package com.project.study_kafka;

import com.project.study_kafka.dto.CarDTO;
import io.github.kattlo.piemok.spring.MockedKafkaConfig;
import io.github.kattlo.piemok.spring.MockedKafkaProducerFactory;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

@Import(MockedKafkaConfig.class)
@SpringBootTest
public class CarProducerTest {

    @Autowired
    DefaultKafkaProducerFactory<String, Object> producerFactory;

    @Autowired
    MockedKafkaProducerFactory<String, Object> producerMockedFactory;

    @Test
    public void should_send_message() {
        var producer = producerMockedFactory.producer();

        producer.ifPresent(p -> {
            var car = CarDTO.builder()
                    .id(UUID.randomUUID().toString())
                    .model("Shelby Mustang")
                    .color("Black")
                    .postedAt(LocalDateTime.now())
                    .build();

            var expected = p.send(new ProducerRecord<>("cars", car));

            var actual = p.history();

            Assertions.assertFalse(actual.isEmpty());
            Assertions.assertEquals(expected, actual.iterator().next());
        });
    }

}
