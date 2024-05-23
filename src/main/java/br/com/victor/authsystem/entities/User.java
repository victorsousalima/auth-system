package br.com.victor.authsystem.entities;

import br.com.victor.authsystem.entities.enums.TypeUser;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements Serializable, UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 14)
    private String phone;

    @Column(name = "type_user")
    @Enumerated(EnumType.STRING)
    private TypeUser typeUser;

    @Column(nullable = false, name = "password_user", length = 150)
    private String password;

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public void updatedAtributes(User newUser) {
        if (newUser.getName() != null && !(newUser.getName().isBlank())) {
            this.setName(newUser.getName());
        }

        if (newUser.getEmail() != null && !(newUser.getEmail().isBlank())) {
            this.setEmail(newUser.getEmail());
        }

        if (newUser.getPhone() != null && !(newUser.getPhone().isBlank())) {
            this.setPhone(newUser.getPhone());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.typeUser == TypeUser.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
