package com.louis.springbootdeepseek.service;

import com.louis.springbootdeepseek.model.DeepSeekRequest;
import com.louis.springbootdeepseek.model.DeepSeekResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DeepSeekService {

    private final WebClient webClient;

    @Value("${deepseek.api.model}")
    private String model;

    @Value("${deepseek.api.temperature}")
    private double temperature;

    @Autowired
    public DeepSeekService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<DeepSeekResponse> generateText(String prompt) {
        DeepSeekRequest request = new DeepSeekRequest();
        request.setModel(model);
        request.setTemperature(temperature);

        // 构建消息列表
        // 这里简化处理，实际使用时可能需要更复杂的消息结构
        // DeepSeek可能有特定的消息格式要求，需要根据其文档调整
        request.setMessages(List.of(
                new com.louis.springbootdeepseek.model.Message("user", prompt)
        ));

        return webClient.post()
                .uri("/v1/chat/completions") // 假设的API路径，实际需要根据DeepSeek文档调整
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(DeepSeekResponse.class);
    }
}
