package com.hypad.Market.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.hypad.Market.configuration.Roles.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 3 , max = 20, message = "Size must be between 3 and 20")
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank
    String token;
}
