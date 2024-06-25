package com.hypad.Market.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User {

    @Id
    private int id;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 3 , max = 20, message = "Size must be between 3 and 20")
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
