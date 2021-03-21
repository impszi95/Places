package com.example.places.repository;

import com.example.places.model.Place;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

    @Repository
    public interface PlaceRepository extends MongoRepository<Place, String> {
//        Place findByEmail(String email);
//        Boolean existsByEmail(String email);
    }
