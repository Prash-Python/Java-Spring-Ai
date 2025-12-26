package com.example.springaidevelopment.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai/v1")
public class ProductControllerWithRag {
    @Autowired
    private VectorStore vectorStore;
    private ChatClient chatClient;
    @Autowired
    public ProductControllerWithRag(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

//    This will ensure that semantic search is very close to context. AI hallucination chances are minimized with RAG.
    @PostMapping("/rag/products")
    public ResponseEntity<String> getProductsWithRag(@RequestParam String query) {
        return ResponseEntity.ok(chatClient
                .prompt(query)
                .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                .call()
                .content());
    }
}
