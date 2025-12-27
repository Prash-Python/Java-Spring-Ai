package com.example.springaidevelopment.imagecontrollers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ai/v1")
public class TextGenFromImageController {
    private ChatClient chatClient;
    private OpenAiImageModel openAiImageModel;
    public TextGenFromImageController(ChatClient.Builder chatClient, OpenAiImageModel openAiImageModel) {
        this.chatClient = chatClient.build();
        this.openAiImageModel = openAiImageModel;
    }

    @PostMapping("/fromimage/getcontext")
    public String getContextFromImage(@RequestParam String query, @RequestParam MultipartFile file) {
        return chatClient
                .prompt()
                .user(user -> user.text(query)
                        .media(MimeTypeUtils.IMAGE_PNG, file.getResource()))
                .call()
                .content();
    }
}
