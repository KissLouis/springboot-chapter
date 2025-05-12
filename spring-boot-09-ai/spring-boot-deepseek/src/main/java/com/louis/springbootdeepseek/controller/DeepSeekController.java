package com.louis.springbootdeepseek.controller;

import com.louis.springbootdeepseek.model.DeepSeekResponse;
import com.louis.springbootdeepseek.service.DeepSeekService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/deepseek")
public class DeepSeekController {

    private final DeepSeekService deepSeekService;

    public DeepSeekController(DeepSeekService deepSeekService) {
        this.deepSeekService = deepSeekService;
    }

    @PostMapping("/generate")
    public Mono<DeepSeekResponse> generateText(@RequestBody String prompt) {
        return deepSeekService.generateText(prompt);
    }
}
