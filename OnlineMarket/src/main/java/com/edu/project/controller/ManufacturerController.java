package com.edu.project.controller;

import com.edu.project.entity.Manufacturer;
import com.edu.project.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService){ this.manufacturerService = manufacturerService; }

    @PostMapping(value = "/market/manufac")
    public ResponseEntity<?> create(@RequestBody Manufacturer manufacturer){
        manufacturerService.create(manufacturer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/market/manufac")
    public ResponseEntity<List<Manufacturer>> findAll(){
        final List<Manufacturer> manufacturerList = manufacturerService.findAll();

        return manufacturerList != null && !manufacturerList.isEmpty()
                ? new ResponseEntity<>(manufacturerList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/market/manufac/{id}")
    public ResponseEntity<Optional<Manufacturer>> find(@PathVariable(name="id") Long id){
        final Optional<Manufacturer> manufacturer = manufacturerService.find(id);

        return manufacturer != null
                ? new ResponseEntity<>(manufacturer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/market/manufac/{id}")
    public ResponseEntity<?> updateManufacturer(@PathVariable(name = "id") Long id, @RequestBody Manufacturer updateManufacturer) {
        return manufacturerService.find(id).map(manufacturer -> {
            manufacturer.setName(updateManufacturer.getName());
            manufacturerService.update(manufacturer);
            return new ResponseEntity<>(manufacturer, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/market/manufac/{id}")
    public ResponseEntity<?> deleteManufacturer(@PathVariable(name = "id") Long id) {
        return manufacturerService.find(id).map(manufacturer -> {
            manufacturerService.delete(manufacturer);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }

}
