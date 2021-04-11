package com.edu.project.service;


import com.edu.project.entity.Product;
import com.edu.project.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public void create(Product product){
        productRepo.save(product);
    }

    public void update(Product product) { productRepo.save(product); }

    public void delete(Product product) { productRepo.delete(product); }

    public List<Product> findAll(){
        return productRepo.findAll();
    }

    public Optional<Product> find(Long id){
        return productRepo.findById(id);
    }
}
