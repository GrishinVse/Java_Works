package com.edu.project.controller;

import com.edu.project.entity.Role;
import com.edu.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){ this.roleService = roleService; }

    @PostMapping(value = "/market/roles")
    public ResponseEntity<?> create (@RequestBody Role role){
        roleService.create(role);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/market/roles")
    public ResponseEntity<List<Role>> findAll(){
        final List<Role> roleList = roleService.findAll();

        return roleList != null && !roleList.isEmpty()
                ? new ResponseEntity<>(roleList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/market/roles/{id}")
    public ResponseEntity<Optional<Role>> find(@PathVariable(name="id") Long id){
        final Optional<Role> role = roleService.find(id);

        return role != null
                ? new ResponseEntity<>(role, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/market/roles/{id}")
    public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id, @RequestBody Role updateRole) {
        return roleService.find(id).map(role -> {
            role.setName(updateRole.getName());
            roleService.update(role);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }).orElseThrow(() -> new IllegalArgumentException());
    }

    @DeleteMapping(value = "/market/roles/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable(name = "id") Long id) {
        return roleService.find(id).map(role -> {
            roleService.delete(role);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException());
    }
}
