package com.hypad.Market.model;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;

    private String productName;

    private String price; //yes, not double
}
