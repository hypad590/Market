package com.hypad.Market.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private int id;

    @NotBlank
    private String name;

    private String productName;

    private String totalPrice; //y

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccVV;

    @NotBlank
    private String ccNumber;

    @Pattern(regexp= "^(0[1-9]|1[0-2])(/)([1-9][0-9])$",
            message="Must be formatted MM/YY")
    private String ccExpiration;
}
