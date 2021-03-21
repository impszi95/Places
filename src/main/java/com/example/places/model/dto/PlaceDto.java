package com.example.places.model.dto;

import com.example.places.model.Address;
import com.example.places.model.OpeningHours;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceDto {
    String name;
    Address address;
    OpeningHoursDto[] openingHours;
}
