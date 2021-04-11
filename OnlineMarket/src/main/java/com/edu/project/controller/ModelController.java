package com.edu.project.controller;

import com.edu.project.entity.Model;
import com.edu.project.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ModelController {
    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService){ this.modelService = modelService; }

    @PostMapping(value = "/market/models")
    public ResponseEntity<?> create(@RequestBody Model model){
        modelService.create(model);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/market/models")
    public ResponseEntity<List<Model>> findAll(){
        final List<Model> modelList = modelService.findAll();

        return modelList != null && !modelList.isEmpty()
                ? new ResponseEntity<>(modelList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/market/models/{id}")
    public ResponseEntity<Optional<Model>> find(@PathVariable(name="id") Long id){
        final Optional<Model> model = modelService.find(id);

        return model != null
                ? new ResponseEntity<>(model, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/market/models/{id}")
    public ResponseEntity<?> updateModel(@PathVariable(name = "id") Long id, @RequestBody Model updateModel) {
        return modelService.find(id).map(model -> {
            model.setName(updateModel.getName());
            model.setVendor_code(updateModel.getVendor_code());
            model.setBrand_id(updateModel.getBrand_id());
            model.setCountry_id(updateModel.getCountry_id());

            modelService.update(model);
            return new ResponseEntity<>(model, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/market/models/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable(name = "id") Long id) {
        return modelService.find(id).map(model -> {
            modelService.delete(model);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
