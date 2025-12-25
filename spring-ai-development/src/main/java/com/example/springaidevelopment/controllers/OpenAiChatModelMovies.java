package com.example.springaidevelopment.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ai/v1")
public class OpenAiChatModelMovies {
    private ChatClient chatClient;
    public OpenAiChatModelMovies(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @PostMapping("/recommend/movies")
    public ResponseEntity<String> getMovies(@RequestParam String genre, @RequestParam String language, @RequestParam String year) {
        String myCustomPrompt = """
                I want to watch a {genre} movie tonight with good rating,\s
                                looking  for movies around this year {year}.\s
                                The  language i am looking for is {language}.
                                Suggest five specific movies and tell me the cast and length of the movie.
                
                                response format should be:
                                1. Movie Name
                                2. basic plot
                                3. cast
                                4. length
                                5. IMDB rating
                """;
        PromptTemplate promptTemplate = new PromptTemplate(myCustomPrompt);
        Prompt prompt = promptTemplate.create(Map.of("genre", genre, "language", language, "year", year));
        return ResponseEntity.ok(chatClient.prompt(prompt).call().content());
    }
}
