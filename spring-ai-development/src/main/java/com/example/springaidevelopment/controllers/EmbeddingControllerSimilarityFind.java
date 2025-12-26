package com.example.springaidevelopment.controllers;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai/v1")
public class EmbeddingControllerSimilarityFind {
    @Autowired
    private EmbeddingModel embeddingModel;

    @RequestMapping("/similarity/search/response")
    public double getSimilarity(@RequestParam String text1, @RequestParam String text2) {
        float[] a = embeddingModel.embed(text1);
        float[] b = embeddingModel.embed(text2);

        double vectorDotProduct = 0.0d;
        double val1 = 0.0d;
        double val2 = 0.0d;

        for (int i = 0; i < a.length; i++) {
            vectorDotProduct += a[i] * b[i];
            val1 += Math.pow(a[i], 2);
            val2 += Math.pow(b[i], 2);
        }

        return vectorDotProduct / (Math.sqrt(val1) * Math.sqrt(val2));
    }
}
