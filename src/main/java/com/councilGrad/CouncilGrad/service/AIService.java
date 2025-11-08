package com.councilGrad.CouncilGrad.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class AIService {

    @Value("${openai.api.key}")
    private String apiKey;

    public String getAIResponse(String prompt) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString("""
                    {
                        "model": "gpt-4o-mini",
                        "messages": [
                            {"role": "system", "content": "You are an expert college eligibility assistant."},
                            {"role": "user", "content": "%s"}
                        ],
                        "response_format": {"type": "json_object"}
                    }
                    """.formatted(prompt)))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"AI service unavailable\"}";
        }
    }
}

