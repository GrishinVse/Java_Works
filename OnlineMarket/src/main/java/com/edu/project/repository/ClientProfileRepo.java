package com.edu.project.repository;

import com.edu.project.entity.ClientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProfileRepo extends JpaRepository<ClientProfile, Long> {
}
