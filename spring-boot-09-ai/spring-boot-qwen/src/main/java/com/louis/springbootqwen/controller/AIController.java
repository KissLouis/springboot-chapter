package com.louis.springbootqwen.controller;

import com.louis.springbootqwen.service.QwenAIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    private final QwenAIService qwenAIService;

    public AIController(QwenAIService qwenAIService) {
        this.qwenAIService = qwenAIService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return qwenAIService.generateResponse(message);
    }
}