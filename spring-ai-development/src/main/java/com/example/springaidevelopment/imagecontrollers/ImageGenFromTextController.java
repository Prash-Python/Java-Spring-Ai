package com.example.springaidevelopment.imagecontrollers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.image.ImageOptions;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/ai/v1")
public class ImageGenFromTextController {
    private final ChatClient chatClient;
    private final OpenAiImageModel openAiImageModel;
    public ImageGenFromTextController(OpenAiImageModel openAiImageModel, ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
        this.openAiImageModel = openAiImageModel;
    }

    // This will return image in the url link generated.
    @GetMapping("/image/{query}")
    public ResponseEntity<String> getImageInUrl(@PathVariable String query) {
        ImagePrompt imagePrompt = new ImagePrompt(query);
        ImageResponse imageResponse = openAiImageModel.call(imagePrompt);
        return ResponseEntity.ok(imageResponse
                .getResult()
                .getOutput()
                .getUrl());
    }

    @GetMapping("/image/prompt/{query}")
    public ResponseEntity<String> getImageResponse(@PathVariable String query) {
        ImageOptions options = OpenAiImageOptions.builder()
                .model("dall-e-3")
                .width(1024)
                .height(1024)
                .N(1)
                .style("natural")
                .quality("hd")
                .build();
        ImagePrompt imagePrompt = new ImagePrompt(query, options);
        ImageResponse imageResponse = openAiImageModel.call(imagePrompt);
        return ResponseEntity.ok(imageResponse
                .getResult()
                .getOutput()
                .getUrl());
    }

    // This will return image in the B64Json generated.
    @PostMapping(value = "/prompt/image", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@RequestParam String text) {
        ImagePrompt imagePrompt = new ImagePrompt(text);
        ImageResponse imageResponse = openAiImageModel.call(imagePrompt);
        return Base64.getDecoder().decode(imageResponse
                .getResult()
                .getOutput()
                .getB64Json());
    }
}
