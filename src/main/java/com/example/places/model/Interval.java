package com.example.places.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@AllArgsConstructor
@Data
public class Interval {

    private LocalTime from;
    private LocalTime to;
}
