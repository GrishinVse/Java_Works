package com.edu.project.repository;

import com.edu.project.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Long> {
}
