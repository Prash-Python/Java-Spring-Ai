package com.example.springaidevelopment.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app/v1")
public class ChatClientController {
    /** ChatClient is more generic to OpenAi or Gemini or Lllama. */
    private ChatClient chatClient;
    public ChatClientController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
    @GetMapping("/response/{messagePrompt}")
    public String getResponse(@PathVariable String messagePrompt){
        return chatClient.prompt(messagePrompt)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();
    }

    @GetMapping("/answer/{prompt}")
    public ResponseEntity<String> getResponseText(@PathVariable String prompt) {
        ChatResponse chatResponse = chatClient.prompt(prompt)
                .call()
                .chatResponse();
        System.out.println("Chat Response: " + chatResponse);
        ChatResponseMetadata chatResponseMetadata = chatResponse.getMetadata();
        System.out.println("Chat Response Metadata: " + chatResponseMetadata);
        String chatModelNumber = chatResponseMetadata.getModel();
        System.out.println("Chat Model Number: " + chatModelNumber);
        return ResponseEntity.ok(chatResponse
                .getResult()
                .getOutput()
                .getText());
    }
}
