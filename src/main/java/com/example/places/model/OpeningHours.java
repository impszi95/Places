package com.example.places.model;

import com.example.places.model.dto.IntervalDto;
import com.example.places.model.dto.OpeningHoursDto;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OpeningHours {
    public enum Day {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }

    private Day day;
    private List<Interval> intervals;

    public OpeningHours(Day day, List<IntervalDto> intervals) {
        this.day = day;
        this.intervals = new ArrayList<>();

        for (IntervalDto intervalDto : intervals){
            String[] fromTimes = intervalDto.getFrom().split(":");
            int fromHour = Integer.parseInt(fromTimes[0]);
            int fromMinute = Integer.parseInt(fromTimes[1]);
            LocalTime from = LocalTime.of(fromHour,fromMinute);

            String[] toTimes = intervalDto.getTo().split(":");
            int toHour = Integer.parseInt(toTimes[0]);
            int toMinute = Integer.parseInt(toTimes[1]);
            LocalTime to = LocalTime.of(toHour,toMinute);

            Interval interval = new Interval(from,to);

            this.intervals.add(interval);
        }

    }
    public OpeningHours(){}

    public boolean isOpen(){
       boolean allIntervalsAreEqual = this.intervals.stream().allMatch(x->x.getFrom().equals(x.getTo()));
        return !allIntervalsAreEqual;
    }
}