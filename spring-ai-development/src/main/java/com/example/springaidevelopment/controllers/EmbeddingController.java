package com.example.springaidevelopment.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai/v1")
public class EmbeddingController {
    // Vector representation of any object in --dimension. These will be set of float values as vector for any object.
    @Autowired
    @Qualifier("openAiEmbeddingModel")
    private EmbeddingModel embeddingModel;

    @PostMapping("/embedding/response")
    public float[] getResponse(@RequestParam String text) {
        return embeddingModel.embed(text);
    }
}
