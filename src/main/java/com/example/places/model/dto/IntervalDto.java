package com.example.places.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@AllArgsConstructor
@Data
public class IntervalDto {

    private String from;
    private String to;
}

