package com.example.places.service;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValiderTest {
    @Autowired
    Valider valider;

    public static TimeTestCase[] TimeTestCases() {
        return new TimeTestCase[]{
                new TimeTestCase("00:00",true),
                new TimeTestCase("08:20",true),
                new TimeTestCase("18:45",true),
                new TimeTestCase("24:00",true),

                new TimeTestCase("000:00",false),
                new TimeTestCase("08A:00",false),
                new TimeTestCase("17:60",false),
                new TimeTestCase("17:99",false),
                new TimeTestCase("0:8:00",false),
                new TimeTestCase("08-00",false),
                new TimeTestCase("-62:00",false),
                new TimeTestCase("50:00",false),
                new TimeTestCase("90:75",false),
        };
    }

    @ParameterizedTest
    @MethodSource(value= "TimeTestCases")
    void isValidTime(TimeTestCase timeTestCase) {
        assertEquals(timeTestCase.valid, valider.isValidTime(timeTestCase.time));
    }
}
@AllArgsConstructor
class TimeTestCase{
    public String time;
    public boolean valid;
}