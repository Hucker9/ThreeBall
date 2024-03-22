package com.threeball.threeballcopy.entities;

import com.threeball.threeballcopy.model.enums.Role;
import com.threeball.threeballcopy.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusers;
    @NotBlank
    @Column(name = "name")
    private String name;
    @NotBlank
    @Column(name = "surname")
    private String surname;
    @Column(name = "year")
    private int year;
    @Email
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "verify")
    private String verify;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "status")
    private Status status;
    @Column(name = "reset_token")
    private String resetToken;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
}
