package com.hypad.Market.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 3 , max = 20, message = "Size must be between 3 and 20")
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
