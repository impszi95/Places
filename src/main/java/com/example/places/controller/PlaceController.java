package com.example.places.controller;

import com.example.places.model.OpeningHours;
import com.example.places.model.Place;
import com.example.places.model.dto.OpeningHoursDto;
import com.example.places.model.dto.PlaceDto;
import com.example.places.service.PlaceService;
import com.example.places.service.Valider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/places")
@CrossOrigin(origins = "*")
public class PlaceController {

    @Autowired
    PlaceService placeService;

    @Autowired
    Valider valider;

    @PostMapping("/create")
        public ResponseEntity<?> CreatePlace(@RequestBody PlaceDto placeDto) {
           try {
               if (!valider.isValidOpeningHours(placeDto.getOpeningHours())){
                   return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
               }
               Place newPlace = placeService.CreatePlace(placeDto);
               return ResponseEntity.ok(newPlace);
           }
           catch (Exception e){
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
           }
    }
    @GetMapping("")
    public ResponseEntity<?> GetPlaces() {
        try {
            return ResponseEntity.ok(placeService.GetAllPlaces());
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/{placeId}")
    public ResponseEntity<?> getPlace(@PathVariable String placeId) {
        try {
            return ResponseEntity.ok(placeService.GetPlace(placeId));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
