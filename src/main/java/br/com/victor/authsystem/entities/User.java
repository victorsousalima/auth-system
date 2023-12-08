package br.com.victor.authsystem.entities;

import br.com.victor.authsystem.entities.enums.TypeUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements Serializable {

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
}
