package com.example.SpringNewWithPostgre.repository;

import com.example.SpringNewWithPostgre.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
