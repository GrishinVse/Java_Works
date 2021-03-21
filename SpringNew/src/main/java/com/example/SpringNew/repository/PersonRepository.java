package com.example.SpringNew.repository;

import com.example.SpringNew.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
