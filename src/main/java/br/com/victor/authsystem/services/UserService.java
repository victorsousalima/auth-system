package br.com.victor.authsystem.services;

import br.com.victor.authsystem.entities.User;
import br.com.victor.authsystem.exceptions.UserExistingException;
import br.com.victor.authsystem.exceptions.UserNotFoundException;
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

    public User findById(Long id) throws Exception {
        User user = userRepository.findById(id).get();

        if (user != null) {
            return user;
        }
        else {
            throw new UserNotFoundException("User does not exist!");
        }
        
    }

    public User createUser(User user) throws Exception {
        User userExists = userRepository.findByEmail(user.getEmail());

        if (userExists != null) {
            throw new UserExistingException("This user already exists!");
        }

        User userCreated = userRepository.save(user);

        return userCreated;
    }

    public User updateUser(Long id, User user) throws Exception {
        User userExists = userRepository.findById(id).get();

        if (userExists == null) {
            throw new UserNotFoundException("User does not exist!");
        }

        setAtributesUserUpdate(userExists, user);

        user = userRepository.save(userExists);

        return user;
        
    }

    public void deleteUser(Long id) throws Exception {
        User userExists = userRepository.findById(id).get();

        if (userExists == null) {
            throw new UserNotFoundException("User does not exist!");
        }

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
