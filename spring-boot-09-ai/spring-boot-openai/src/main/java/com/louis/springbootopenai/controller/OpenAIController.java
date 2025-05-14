package com.louis.springbootopenai.controller;

import com.louis.springbootopenai.service.OpenAIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenAIController {

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @GetMapping("/openai/completion")
    public String getCompletion(@RequestParam String prompt) {
        return openAIService.getCompletion(prompt);
    }

}
