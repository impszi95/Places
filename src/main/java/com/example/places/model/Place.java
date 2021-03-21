package com.example.places.model;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Place {
    @Id
    String id;
    String name;
    Address address;
    OpeningHours[] openingHours;

    public Place (String name, Address address, OpeningHours[] openingHours){
        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
    }
    public Place (){
    }
}
