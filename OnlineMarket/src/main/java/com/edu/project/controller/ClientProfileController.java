package com.edu.project.controller;

import com.edu.project.entity.ClientProfile;
import com.edu.project.service.ClientProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientProfileController {
    private final ClientProfileService clientProfileService;

    @Autowired
    public ClientProfileController(ClientProfileService clientProfileService){ this.clientProfileService = clientProfileService; }

    @PostMapping(value = "/market/clt_profiles")
    public ResponseEntity<?> create (@RequestBody ClientProfile clientProfile){
        clientProfileService.create(clientProfile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/market/clt_profiles")
    public ResponseEntity<List<ClientProfile>> findAll(){
        final List<ClientProfile> categoryList = clientProfileService.findAll();

        return categoryList != null && !categoryList.isEmpty()
                ? new ResponseEntity<>(categoryList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/market/clt_profiles/{id}")
    public ResponseEntity<Optional<ClientProfile>> find(@PathVariable(name="id") Long id){
        final Optional<ClientProfile> person = clientProfileService.find(id);

        return person != null
                ? new ResponseEntity<>(person, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/market/clt_profiles/{id}")
    public ResponseEntity<?> updateClientProfile(@PathVariable(name = "id") Long id, @RequestBody ClientProfile updateClientProfile) {
        return clientProfileService.find(id).map(clientProfile -> {
            clientProfile.setClient(updateClientProfile.getClient());
            clientProfile.setFirst_name(updateClientProfile.getFirst_name());
            clientProfile.setLast_name(updateClientProfile.getLast_name());
            clientProfile.setFather_name(updateClientProfile.getFather_name());
            clientProfile.setAddress(updateClientProfile.getAddress());
            clientProfile.setBirth_date(updateClientProfile.getBirth_date());
            clientProfileService.update(clientProfile);
            return new ResponseEntity<>(clientProfile, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/market/clt_profiles/{id}")
    public ResponseEntity<?> deleteClientProfile(@PathVariable(name = "id") Long id) {
        return clientProfileService.find(id).map(clientProfile -> {
            clientProfileService.delete(clientProfile);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
