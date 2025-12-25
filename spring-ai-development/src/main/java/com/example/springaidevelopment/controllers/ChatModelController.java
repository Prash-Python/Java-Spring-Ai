package com.example.springaidevelopment.controllers;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat/v1")
public class ChatModelController {
    /** ChatModel is specific to OpenAi or Gemini or Lllama. */
    private OpenAiChatModel chatModel;
    public ChatModelController(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
    }
    @GetMapping("/textresponse/{messagePrompt}")
    public String getTextResponse(@PathVariable String messagePrompt) {
        return chatModel.call(messagePrompt);
    }
}
