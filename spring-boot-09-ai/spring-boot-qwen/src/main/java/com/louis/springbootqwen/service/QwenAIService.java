package com.louis.springbootqwen.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class QwenAIService {

    private final ChatClient chatClient;

    public QwenAIService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String generateResponse(String prompt) {
        return chatClient.call(prompt);
    }
}

