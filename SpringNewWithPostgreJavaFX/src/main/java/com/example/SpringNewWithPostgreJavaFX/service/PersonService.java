package com.example.SpringNewWithPostgreJavaFX.service;

import com.example.SpringNewWithPostgreJavaFX.entity.Person;
import com.example.SpringNewWithPostgreJavaFX.repository.PersonRepository;
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

    public void update(Person person) { personRepository.save(person); }

    public void delete(Person person) { personRepository.delete(person); }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Optional<Person> find(Long id){
        return personRepository.findById(id);
    }
}
