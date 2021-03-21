package com.example.places.service;

import com.example.places.model.Interval;
import com.example.places.model.dto.IntervalDto;
import com.example.places.model.dto.OpeningHoursDto;
import com.example.places.model.dto.PlaceDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Valider {
    public boolean isValidOpeningHours(OpeningHoursDto[] openingHoursDtos){
        for (OpeningHoursDto openingHoursDto : openingHoursDtos){
            for (IntervalDto intervalDto : openingHoursDto.getIntervals()){
                boolean isValidIntervals = isValidTime(intervalDto.getFrom())
                        && isValidTime(intervalDto.getTo());
                if (!isValidIntervals){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isValidTime(String time){
        try {
            String[] times = time.split(":");

            if (times.length != 2){
                return false;
            }
            if (times[0].length()!=2 || times[1].length()!=2){
                return false;
            }

            int hours = Integer.parseInt(times[0]);
            int minutes = Integer.parseInt(times[1]);
            if (hours < 0 || hours > 24 || minutes < 0 || minutes >= 60){
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
