package br.com.victor.authsystem.controllers;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.victor.authsystem.dto.UserRequest;
import br.com.victor.authsystem.dto.UserResponse;
import br.com.victor.authsystem.entities.User;
import br.com.victor.authsystem.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> findAll() {
        List<User> users = service.findAll();

        List<UserResponse> usersResponse = users.stream().map(u -> new UserResponse(u)).toList();

        return ResponseEntity.ok().body(usersResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        User user = service.findById(id);

        return ResponseEntity.ok().body(new UserResponse(user));
    }

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody UserRequest userRequest) {
        User user = service.createUser(userRequest.convertToUser());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
    
}
