package br.com.victor.authsystem.services;

import br.com.victor.authsystem.entities.User;
import br.com.victor.authsystem.exceptions.UserExistingException;
import br.com.victor.authsystem.exceptions.UserNotFoundException;
import br.com.victor.authsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> findAll() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new UserNotFoundException("No registered users!");
        }

        return users;
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElseThrow(() -> new UserNotFoundException("User does not exist!"));
    }

    public User createUser(User user) {
        User userExists = userRepository.findByEmail(user.getEmail());

        if (userExists != null) {
            throw new UserExistingException("This user already exists!");
        }

        User userCreated = userRepository.save(user);

        return userCreated;
    }

    public User updateUser(User user) {
        User userExists = findById(user.getId());

        setAtributesUserUpdate(userExists, user);

        user = userRepository.save(userExists);

        return user;
        
    }

    public void deleteUser(Long id) {
        User userExists = findById(id);

        userRepository.delete(userExists);
    }

    private void setAtributesUserUpdate(User source, User target) {
        if (target.getName() != null) {
            source.setName(target.getName());
        }

        if (target.getEmail() != null) {
            source.setEmail(target.getEmail());
        }

        if (target.getPhone() != null) {
            source.setPhone(target.getPhone());
        }

        if (target.getPassword() != null) {
            source.setPassword(target.getPassword());
        }
    }
}
