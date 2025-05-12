package com.louis.springbootdeepseek.model;


import lombok.Data;

@Data
class Choice {
    private int index;
    private Message message;
    private String finish_reason;
}