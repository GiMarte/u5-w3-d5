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
        @Enumerated(EnumType.STRING)
        private Role role;
        private String username;
        private String password;
        private String name;
        private String surname;

        public User(Role role, String username, String password, String name, String surname) {
    this.role = role;
    this.username = username;
    this.password = password;
    this.name = name;
    this.surname = surname;
}}
