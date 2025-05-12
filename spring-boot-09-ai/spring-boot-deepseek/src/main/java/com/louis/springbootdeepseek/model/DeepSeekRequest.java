package com.louis.springbootdeepseek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeepSeekRequest {
    private String model;
    private List<Message> messages;
    private double temperature;
}


