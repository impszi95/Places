package com.example.places.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String country;
    private String state;
    private String city;
    private String street;
    private int streetNum;

    public boolean Equals(Address otherAddress){
        return this.country.equals(otherAddress.country) && this.city.equals(otherAddress.city)
                && this.state.equals(otherAddress.state) && this.street.equals(otherAddress.street) && this.streetNum == otherAddress.streetNum;
    }
}

