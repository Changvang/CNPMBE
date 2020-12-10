package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.*;

import com.example.demo.repository.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AccommodationController {

    @Autowired
    AccommodationRepository accRepo;

    @GetMapping("/accommodations")
    public ResponseEntity<List<Accommodation>> getAllAccommodation() {
        try {
            List<Accommodation> accommodations = new ArrayList<Accommodation>();

            // accRepo.findByTitleContaining(serId).forEach(demos::add);

            accRepo.findAll().forEach(accommodations::add);

            if (accommodations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(accommodations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/accommodations/{id}")
    public ResponseEntity<Accommodation> getAccomodationsById(@PathVariable("id") long id) {
        Optional<Accommodation> demoData = accRepo.findById(id);

        if (demoData.isPresent()) {
            return new ResponseEntity<>(demoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/accommodations/getByOwnerID/{id}")
    public ResponseEntity<List<Accommodation>> getAccomodationsByOwnerId(@PathVariable("id") long id) {
        try {
            List<Accommodation> accommodations = new ArrayList<Accommodation>();

            accRepo.findAllByuserID(id).forEach(accommodations::add);

            if (accommodations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(accommodations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/accommodations/searchByAddress/{address}")
    public ResponseEntity<List<Accommodation>> getAccomodationsByAddress(@PathVariable("address") String address) {
        try {
            List<Accommodation> accommodations = new ArrayList<Accommodation>();
            accRepo.findByAddressContaining(address).forEach(accommodations::add);


            if (accommodations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(accommodations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/accommodations")
    public ResponseEntity<Accommodation> createAccommodation(@RequestBody Accommodation acc) {
        try {
            Accommodation _demo = accRepo.save(new Accommodation(acc.getPrice(),acc.getArea(),acc.getFacility(),acc.getAddress(),acc.getUserID()));
            return new ResponseEntity<>(_demo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
   

}