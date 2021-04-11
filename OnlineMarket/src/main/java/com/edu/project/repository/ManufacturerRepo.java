package com.edu.project.repository;

import com.edu.project.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepo extends JpaRepository<Manufacturer, Long> {
}
