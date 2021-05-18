package com.project.study_kafka_consumer.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDTO implements Serializable {

    private String id;
    private String model;
    private String color;
    private String postedAt;

    @Override
    public String toString() {
        return "CarDTO { id=" + id  + ", model=" + model + ", color=" + color + ", postedAt=" + postedAt + "}";
    }
}

