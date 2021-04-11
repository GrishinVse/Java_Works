package com.edu.project.service;

import com.edu.project.entity.Manufacturer;
import com.edu.project.repository.ManufacturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {
    @Autowired
    private ManufacturerRepo manufacturerRepo;

    public void create(Manufacturer manufacturer){
        manufacturerRepo.save(manufacturer);
    }

    public void update(Manufacturer manufacturer) { manufacturerRepo.save(manufacturer); }

    public void delete(Manufacturer manufacturer) { manufacturerRepo.delete(manufacturer); }

    public List<Manufacturer> findAll(){
        return manufacturerRepo.findAll();
    }

    public Optional<Manufacturer> find(Long id){
        return manufacturerRepo.findById(id);
    }
}
