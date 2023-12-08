package br.com.victor.authsystem.controllers;


import br.com.victor.authsystem.dto.UserCreateRequest;
import br.com.victor.authsystem.dto.UserResponse;
import br.com.victor.authsystem.dto.UserUpdateCredentialsRequest;
import br.com.victor.authsystem.entities.User;
import br.com.victor.authsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<Void> create(@RequestBody UserCreateRequest userCreateRequest) {
        User user = service.createUser(userCreateRequest.convertToUser());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody UserUpdateCredentialsRequest userUpdateCredentials) {
        User userUpdated = service.updateCredentialsUser(userUpdateCredentials.convertToUser(id));

        return ResponseEntity.ok().body(new UserResponse(userUpdated));
    }
    
}
