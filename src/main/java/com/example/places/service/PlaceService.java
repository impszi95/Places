package com.example.places.service;
import com.example.places.model.OpeningHours;
import com.example.places.model.Place;
import com.example.places.model.dto.OpeningHoursDto;
import com.example.places.model.dto.PlaceDto;
import com.example.places.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlaceService {
    @Autowired
    PlaceRepository placeRepository;

    public Place CreatePlace(PlaceDto placeDto){
        OpeningHours[] openingHoursA = new OpeningHours[7];
        for (int i = 0; i<openingHoursA.length;i++){
            openingHoursA[i] = placeDto.getOpeningHours()[i].BuildOpeningHours();
        }

       Place newPlace = new Place(placeDto.getName(), placeDto.getAddress(), openingHoursA);
       placeRepository.save(newPlace);
       return newPlace;
    }

    public List<Place> GetAllPlaces() {
        return placeRepository.findAll();
    }

    public Place GetPlace(String placeId) throws Exception {
        return placeRepository.findById(placeId).orElseThrow(() -> new NoSuchElementException("Place not found with id:" + placeId));
    }
}
