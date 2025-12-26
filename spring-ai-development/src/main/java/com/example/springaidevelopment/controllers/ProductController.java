package com.example.springaidevelopment.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ai/v1")
public class ProductController {
    private ChatClient chatClient;
    @Autowired
    private VectorStore vectorStore;
    @Autowired
    public ProductController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @PostMapping("/products")
    public List<Document> getProducts(@RequestParam String text) {
//        This will return only 2 documents. from top of semantic search.
//        vectorStore.similaritySearch(SearchRequest.builder().query(text).topK(2).build());
//        This will return all matching documents from semantic search.
        return vectorStore.similaritySearch(text);
    }
}
