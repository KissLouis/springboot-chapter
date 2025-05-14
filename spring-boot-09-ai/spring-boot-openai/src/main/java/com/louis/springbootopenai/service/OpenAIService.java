package com.louis.springbootopenai.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

    private final OpenAiService openAiService;


    public OpenAIService(@Value("${openai.api-key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }

    public String getCompletion(String prompt) {
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(prompt)
                .maxTokens(150)
                .temperature(0.7)
                .build();
        return openAiService.createCompletion(completionRequest).getChoices().get(0).getText();
    }
}
