package com.example.springaidevelopment.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app/v2")
public class ChatClientWithAdvisorsController {
    private ChatClient chatClient;
    /**
     * This gives advisors that can remember the previous chat with the user and
     * those chats history, such that communication can go ahead with the agent.
     */
    private ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
    public ChatClientWithAdvisorsController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient
                .defaultAdvisors(MessageChatMemoryAdvisor
                        .builder(chatMemory)
                        .build())
                .build();
    }

    @GetMapping("/answer1/{prompt}")
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
