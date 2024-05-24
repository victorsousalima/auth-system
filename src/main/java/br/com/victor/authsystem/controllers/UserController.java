package br.com.victor.authsystem.controllers;


import br.com.victor.authsystem.dto.UserCreateRequest;
import br.com.victor.authsystem.dto.UserResponse;
import br.com.victor.authsystem.dto.UserUpdateCredentialsRequest;
import br.com.victor.authsystem.dto.UserUpdatePasswordRequest;
import br.com.victor.authsystem.entities.User;
import br.com.victor.authsystem.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_ADMIN")
    public ResponseEntity<List<UserResponse>> findAll() {
        List<User> users = service.findAll();

        List<UserResponse> usersResponse = users.stream().map(u -> new UserResponse(u)).toList();

        return ResponseEntity.ok().body(usersResponse);
    }

    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        User user = service.findById(id);

        return ResponseEntity.ok().body(new UserResponse(user));
    }

    @PostMapping()
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        User user = service.createUser(userCreateRequest.convertToUser());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserResponse(user));
    }

    @PutMapping("/updateCredentials/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody @Valid UserUpdateCredentialsRequest userUpdateCredentials) {
        User userUpdated = service.updateCredentialsUser(userUpdateCredentials.convertToUser(id));

        return ResponseEntity.ok().body(new UserResponse(userUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteUser(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid UserUpdatePasswordRequest userUpdatePasswordRequest) {
        service.updatePasswordUser(id, userUpdatePasswordRequest);

        return ResponseEntity.noContent().build();
    }
}
