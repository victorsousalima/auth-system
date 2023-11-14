package br.com.victor.authsystem.services;

import br.com.victor.authsystem.entities.User;
import br.com.victor.authsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAll() {
        List<User> users = userRepository.findAll();

        return users;
    }

    public User findById(Long id) {
        User user = userRepository.findById(id).get();

        return user;
    }
}
