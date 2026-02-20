package gianmarte.u5w3d5.entities;


import gianmarte.u5w3d5.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String username;
        @Column(nullable = false)
        private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(Role role, String password, String username) {
    this.role = role;
    this.password = password;
    this.username = username;
}}
