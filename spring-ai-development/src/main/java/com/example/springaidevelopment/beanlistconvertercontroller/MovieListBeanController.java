package com.example.springaidevelopment.beanlistconvertercontroller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/ai/v1")
public class MovieListBeanController {
    private ChatClient chatClient;
    public MovieListBeanController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
    BeanOutputConverter<List<Movie>> outputConverter =
            new BeanOutputConverter<>(new ParameterizedTypeReference<List<Movie>>() {
    });

    /** This will return a list of movies in json format. */
    @GetMapping("/movies/details/list")
    public List<Movie> getMovies(@RequestParam String name) {
        String message = """
                List top 5 movies of {name}
                {format}
                """;
        PromptTemplate promptTemplate = new PromptTemplate(message);
        Prompt prompt = promptTemplate.create(Map.of("name", name, "format", outputConverter.getFormat()));
        List<Movie> movies = outputConverter.convert(Objects.requireNonNull(chatClient.prompt(prompt).call().content()));
        return movies;
    }
}
