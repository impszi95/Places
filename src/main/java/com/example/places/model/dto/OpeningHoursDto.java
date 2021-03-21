package com.example.places.model.dto;

import com.example.places.model.Interval;
import com.example.places.model.OpeningHours;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OpeningHoursDto {
    private OpeningHours.Day day;
    private List<IntervalDto> intervals;


    public OpeningHours BuildOpeningHours(){
        return new OpeningHours(this.getDay(),this.intervals);
    }
}
