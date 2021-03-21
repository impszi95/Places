package com.example.places.controller;

import com.example.places.model.Address;
import com.example.places.model.Interval;
import com.example.places.model.OpeningHours;
import com.example.places.model.Place;
import com.example.places.model.dto.IntervalDto;
import com.example.places.model.dto.OpeningHoursDto;
import com.example.places.model.dto.PlaceDto;
import com.example.places.repository.PlaceRepository;
import com.example.places.service.PlaceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PlaceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    PlaceRepository placeRepository;

    public static PlaceDto[] PlaceTestCases() {
        return new PlaceDto[]{
          new PlaceDto(
                  "Burger King",
                  new Address("Hungary", "Pest", "Budapest", "main street", 35),
                  new OpeningHoursDto[]{
                          new OpeningHoursDto(OpeningHours.Day.Monday,
                                  new ArrayList<>(){{add(new IntervalDto("08:00","12:00"));add(new IntervalDto("14:00","00:00"));}}),
                          new OpeningHoursDto(OpeningHours.Day.Tuesday,
                                  new ArrayList<>(){{add(new IntervalDto("08:00","12:00"));}}),
                          new OpeningHoursDto(OpeningHours.Day.Wednesday,
                                  new ArrayList<>(){{add(new IntervalDto("08:00", "16:00"));}}),
                          new OpeningHoursDto(OpeningHours.Day.Thursday,
                                  new ArrayList<>(){{add(new IntervalDto("08:00", "16:00"));}}),
                          new OpeningHoursDto(OpeningHours.Day.Friday,
                                  new ArrayList<>(){{add(new IntervalDto("09:00", "17:00"));}}),
                          new OpeningHoursDto(OpeningHours.Day.Saturday,
                                  new ArrayList<>(){{add(new IntervalDto("09:00", "12:00"));}}),
                          new OpeningHoursDto(OpeningHours.Day.Sunday,
                                  new ArrayList<>(){{add(new IntervalDto("00:00", "00:00"));}})
                  }),
                new PlaceDto(
                        "Vapiano",
                        new Address("Switzerland", "canton of Zurich", "Zurich", "main street", 44),
                        new OpeningHoursDto[]{
                                new OpeningHoursDto(OpeningHours.Day.Monday,
                                        new ArrayList<>(){{add(new IntervalDto("09:00","11:00"));add(new IntervalDto("12:00","23:00"));}}),
                                new OpeningHoursDto(OpeningHours.Day.Tuesday,
                                        new ArrayList<>(){{add(new IntervalDto("08:00","12:00"));add(new IntervalDto("08:00","12:00"));}}),
                                new OpeningHoursDto(OpeningHours.Day.Wednesday,
                                        new ArrayList<>(){{add(new IntervalDto("08:00", "16:00"));}}),
                                new OpeningHoursDto(OpeningHours.Day.Thursday,
                                        new ArrayList<>(){{add(new IntervalDto("08:00", "16:00"));}}),
                                new OpeningHoursDto(OpeningHours.Day.Friday,
                                        new ArrayList<>(){{add(new IntervalDto("09:00", "17:00"));}}),
                                new OpeningHoursDto(OpeningHours.Day.Saturday,
                                        new ArrayList<>(){{add(new IntervalDto("09:00", "09:00"));}}),
                                new OpeningHoursDto(OpeningHours.Day.Sunday,
                                        new ArrayList<>(){{add(new IntervalDto("00:00", "00:00"));}})
                        }),
        };
    }
    @BeforeEach
    private void clearDb(){ //Normally should use an in-mem.db of course
        placeRepository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource(value= "PlaceTestCases")
    public void TestCreatePlace(PlaceDto placetestCase) throws Exception {
        mockMvc.perform(post("/places/create", 42L)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(placetestCase)))
                .andExpect(status().isOk());

        assertEquals(placeRepository.count(),1);
        Place place = placeRepository.findAll().get(0);

        String expectedName = placetestCase.getName();
        Address expectedAddress = placetestCase.getAddress();
        assertEquals(place.getName(), expectedName);
        assertEquals(place.getAddress(), expectedAddress);

        int expectedOpeningHoursAmount = placetestCase.getOpeningHours().length;
        assertEquals(place.getOpeningHours().length, expectedOpeningHoursAmount);

        for (int i = 0; i<expectedOpeningHoursAmount; i++){
            OpeningHours.Day expectedDay = placetestCase.getOpeningHours()[i].getDay();
            assertEquals(place.getOpeningHours()[i].getDay(), expectedDay);

            int expectedIntervalAmount = placetestCase.getOpeningHours()[i].getIntervals().size();
            assertEquals(place.getOpeningHours()[i].getIntervals().size(), expectedIntervalAmount);

            for (int j = 0; j<expectedIntervalAmount;j++){
                Interval expectedInterval = BuildIntervalFromDto(placetestCase.getOpeningHours()[i].getIntervals().get(j));
                assertEquals(place.getOpeningHours()[i].getIntervals().get(j), expectedInterval);
            }
        }
    }
    private Interval BuildIntervalFromDto(IntervalDto intervalDto){
        String[] fromTimes = intervalDto.getFrom().split(":");
        int fromHour = Integer.parseInt(fromTimes[0]);
        int fromMinute = Integer.parseInt(fromTimes[1]);
        LocalTime from = LocalTime.of(fromHour,fromMinute);

        String[] toTimes = intervalDto.getTo().split(":");
        int toHour = Integer.parseInt(toTimes[0]);
        int toMinute = Integer.parseInt(toTimes[1]);
        LocalTime to = LocalTime.of(toHour,toMinute);

        return new Interval(from,to);
    }
}
