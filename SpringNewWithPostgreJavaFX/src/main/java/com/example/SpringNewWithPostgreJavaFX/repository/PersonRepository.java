package com.example.SpringNewWithPostgreJavaFX.repository;

import com.example.SpringNewWithPostgreJavaFX.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
