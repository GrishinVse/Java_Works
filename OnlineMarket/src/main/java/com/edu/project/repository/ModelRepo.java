package com.edu.project.repository;

import com.edu.project.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepo extends JpaRepository<Model, Long> {
}
