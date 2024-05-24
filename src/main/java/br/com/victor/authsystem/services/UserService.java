package br.com.victor.authsystem.services;

import br.com.victor.authsystem.dto.UserUpdatePasswordRequest;
import br.com.victor.authsystem.entities.User;
import br.com.victor.authsystem.exceptions.PasswordUpdateConfirmationException;
import br.com.victor.authsystem.exceptions.UserExistingException;
import br.com.victor.authsystem.exceptions.UserNotFoundException;
import br.com.victor.authsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public User createUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User userExists = userRepository.findByEmail(user.getEmail());

        if (userExists != null)
            throw new UserExistingException("This user already exists!");

        User userCreated = userRepository.save(user);

        return userCreated;
    }

    @Transactional
    public User updateCredentialsUser(User user) {
        User userExists = findById(user.getId());

        userExists.updatedAtributes(user);

        return userExists;
    }

    @Transactional
    public void updatePasswordUser(Long id, UserUpdatePasswordRequest requestData) {
        User user = userRepository.getReferenceById(id);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(requestData.actualPassword(), user.getPassword()))
            throw new BadCredentialsException("Invalid password!");

        if (!requestData.newPassword().equals(requestData.newPasswordConfirmation()))
            throw new PasswordUpdateConfirmationException("The new password doesn't match your confirmation!");

        String newPasswordEncoded = encoder.encode(requestData.newPassword());

        user.setPassword(newPasswordEncoded);
    }

    public void deleteUser(Long id) {
        User userExists = findById(id);

        userRepository.delete(userExists);
    }

}
