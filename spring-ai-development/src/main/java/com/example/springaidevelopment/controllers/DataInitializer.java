package com.example.springaidevelopment.controllers;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer {
    @Autowired
    private VectorStore vectorStore;

    @PostConstruct // This will auto initialize at the start.
    public void initData() {
        TextReader textReader = new TextReader(new ClassPathResource("product_details.txt"));
        TokenTextSplitter splitter = new TokenTextSplitter();
        List<Document> documents = splitter.split(textReader.get());
        vectorStore.add(documents);
    }
}
