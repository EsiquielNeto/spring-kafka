package com.project.study_kafka.controller;

import com.project.study_kafka.dto.CarDTO;
import com.project.study_kafka.producer.CarProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("cars")
public class CarController {

    @Autowired
    private CarProducer carProducer;

    @PostMapping
    public ResponseEntity<CarDTO> create(@RequestBody CarDTO carDTO) {
        CarDTO car = CarDTO.builder()
                .id(UUID.randomUUID().toString())
                .color(carDTO.getColor())
                .model(carDTO.getModel())
                .postedAt(LocalDateTime.now())
                .build();

        carProducer.send(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(car);
    }
}
