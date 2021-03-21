package com.example.SpringNew.service;

import com.example.SpringNew.entity.Person;
import com.example.SpringNew.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public void create(Person person){
        personRepository.save(person);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> find(Long id){
        return personRepository.findById(id);
    }
}
