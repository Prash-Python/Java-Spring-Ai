package com.example.springaidevelopment.beanconvertercontroller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/ai/v1")
public class MovieBeanController {
    private ChatClient chatClient;
    public MovieBeanController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
    BeanOutputConverter<Movie> outputConverter = new BeanOutputConverter<>(Movie.class);

    /** This will return a single movie in desired json format values */
    @GetMapping("/movie/details")
    public Movie getMovieDetails(@RequestParam String name) {
        String message = """
                Provide a best movie of {name}
                {format}
                """;
        PromptTemplate promptTemplate = new PromptTemplate(message);
        Prompt prompt = promptTemplate.create(Map.of("name", name, "format", outputConverter.getFormat()));
        return
                outputConverter.convert(Objects.requireNonNull(chatClient.prompt(prompt).call().content()));

    }
}
