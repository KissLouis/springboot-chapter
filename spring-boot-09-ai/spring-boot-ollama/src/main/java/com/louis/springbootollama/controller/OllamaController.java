package com.louis.springbootollama.controller;

import com.louis.springbootollama.service.OllamaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {

    private final OllamaService ollamaService;

    public OllamaController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @GetMapping("/generate")
    public String generate(@RequestParam String model, @RequestParam String prompt) {
        return ollamaService.generateResponse(model, prompt);
    }
}