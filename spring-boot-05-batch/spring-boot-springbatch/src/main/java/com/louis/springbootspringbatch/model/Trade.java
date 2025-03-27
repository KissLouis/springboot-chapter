package com.louis.springbootspringbatch.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Trade {
    @Id
    private String id;
    private String symbol;
    private BigDecimal price;
    private int quantity;

}