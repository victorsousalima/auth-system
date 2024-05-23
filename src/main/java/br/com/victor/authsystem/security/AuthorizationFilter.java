package br.com.victor.authsystem.security;

import br.com.victor.authsystem.entities.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getTokenHeader(request);

        if (token != null) {
            var subject = tokenService.verifyToken(token);

            authenticationUser(subject);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenHeader(HttpServletRequest request) {
        var header = request.getHeader("Authorization");

        if (header != null)
            return header.split(" ")[1];

        return null;
    }

    private void authenticationUser(String username) {
        var user = (User) authenticationService.loadUserByUsername(username);

        var userAuthentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
    }
}
