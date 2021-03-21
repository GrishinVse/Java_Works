package com.example.SpringNew.controller;

import com.example.SpringNew.entity.Person;
import com.example.SpringNew.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestApiController {
    private final PersonService personService;

    @Autowired
    public RestApiController(PersonService personService){
        this.personService = personService;
    }

    @PostMapping(value = "/api/persons")
    public ResponseEntity<?> create (@RequestBody Person person){
        personService.create(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/api/persons")
    public ResponseEntity<List<Person>> findAll(){
        final List<Person> personList = personService.findAll();

        return personList != null && !personList.isEmpty()
                ? new ResponseEntity<>(personList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/persons/{id}")
    public ResponseEntity<Optional<Person>> find(@PathVariable(name="id") Long id){
        final Optional<Person> person = personService.find(id);

        return person != null
                ? new ResponseEntity<>(person, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
