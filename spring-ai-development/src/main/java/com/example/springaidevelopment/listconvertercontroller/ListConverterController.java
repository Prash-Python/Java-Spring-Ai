package com.example.springaidevelopment.listconvertercontroller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/ai/v1")
public class ListConverterController {
    private ChatClient chatClient;
    public ListConverterController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
    ListOutputConverter outputConverter = new ListOutputConverter(new DefaultConversionService());

    /** This will return a list of movies */
    @GetMapping("/movies/list")
    public List<String> getResponseContents(@RequestParam String name) {
        String message = """
                List top 5 movies of {name}
                {format}
                """;
        PromptTemplate promptTemplate = new PromptTemplate(message);
        Prompt prompt = promptTemplate.create(Map.of("name", name, "format", outputConverter.getFormat()));
        var response = chatClient.prompt(prompt).call().content();
        return outputConverter.convert(Objects.requireNonNull(response));


    }
}
