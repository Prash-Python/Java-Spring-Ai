package com.example.springaidevelopment.audioservices;

import org.springframework.stereotype.Service;

@Service
public interface TextToSpeechService {
    byte[] getAudioFromText(String text);
}
