package com.edu.project.service;

import com.edu.project.entity.ClientProfile;
import com.edu.project.repository.ClientProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientProfileService {
    @Autowired
    private ClientProfileRepo clientProfileRepo;

    public void create(ClientProfile clientProfile){
        clientProfileRepo.save(clientProfile);
    }

    public void update(ClientProfile clientProfile) { clientProfileRepo.save(clientProfile); }

    public void delete(ClientProfile clientProfile) { clientProfileRepo.delete(clientProfile); }

    public List<ClientProfile> findAll(){
        return clientProfileRepo.findAll();
    }

    public Optional<ClientProfile> find(Long id){
        return clientProfileRepo.findById(id);
    }
}
