package com.example.springaidevelopment.audiocontrollers;

import com.example.springaidevelopment.audioservices.TextToSpeechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai/v1")
public class TextToSpeechGenController {
    @Autowired
    private TextToSpeechService textToSpeechService;
    @PostMapping(value = "/speech/tts", produces = "audio/mpeg")
    public byte[] getAudioFromText(@RequestParam String text) {
        return textToSpeechService.getAudioFromText(text);
    }
}
