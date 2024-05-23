package br.com.victor.authsystem.security;

import br.com.victor.authsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userDetails = (UserDetails) userRepository.findByEmail(username);

        if (userDetails == null)
            throw new UsernameNotFoundException("Usuário inexistente ou senha inválida");

        return userDetails;
    }
}
