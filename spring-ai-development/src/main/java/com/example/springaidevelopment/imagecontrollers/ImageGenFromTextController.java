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
    private final OpenAiImageModel openAiImageModel;
    public ImageGenFromTextController(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    // This will return image in the url link generated.
    @GetMapping("/image/{query}")
    public ResponseEntity<String> getImageInUrl(@PathVariable String query) {
        ImageOptions options = OpenAiImageOptions.builder()
                .quality("hd")
                .style("natural")
                .model("dall-e-3")
                .N(1)
                .width(1024)
                .height(1024)
                .build();
        ImagePrompt imagePrompt = new ImagePrompt(query, options);
        ImageResponse imageResponse = openAiImageModel.call(imagePrompt);
        return ResponseEntity.ok(imageResponse
                .getResult()
                .getOutput()
                .getUrl());
    }
}
