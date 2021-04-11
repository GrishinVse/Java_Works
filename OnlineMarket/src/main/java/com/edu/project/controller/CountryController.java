package com.edu.project.controller;

import com.edu.project.entity.Country;
import com.edu.project.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CountryController {
    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService){ this.countryService = countryService; }

    @PostMapping(value = "/market/countries")
    public ResponseEntity<?> create(@RequestBody Country country){
        countryService.create(country);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/market/countries")
    public ResponseEntity<List<Country>> findAll(){
        final List<Country> countryList = countryService.findAll();

        return countryList != null && !countryList.isEmpty()
                ? new ResponseEntity<>(countryList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/market/countries/{id}")
    public ResponseEntity<Optional<Country>> find(@PathVariable(name="id") Long id){
        final Optional<Country> country = countryService.find(id);

        return country != null
                ? new ResponseEntity<>(country, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/market/countries/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable(name = "id") Long id, @RequestBody Country updateCountry) {
        return countryService.find(id).map(country -> {
            country.setName(updateCountry.getName());
            countryService.update(country);
            return new ResponseEntity<>(country, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/market/countries/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable(name = "id") Long id) {
        return countryService.find(id).map(country -> {
            countryService.delete(country);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
