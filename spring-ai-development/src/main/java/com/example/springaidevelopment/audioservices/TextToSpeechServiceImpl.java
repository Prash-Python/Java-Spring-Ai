package com.example.springaidevelopment.audioservices;

import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.stereotype.Service;

@Service
public class TextToSpeechServiceImpl implements TextToSpeechService {
    private OpenAiAudioSpeechModel speechModel;
    public TextToSpeechServiceImpl(OpenAiAudioSpeechModel speechModel) {
        this.speechModel = speechModel;
    }
    @Override
    public byte[] getAudioFromText(String text) {
        OpenAiAudioSpeechOptions options = OpenAiAudioSpeechOptions.builder()
                .model(OpenAiAudioApi.TtsModel.TTS_1_HD.value)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .voice(OpenAiAudioApi.SpeechRequest.Voice.ECHO)
                .speed(1.0d)
                .build();
        TextToSpeechPrompt prompt = new TextToSpeechPrompt(text, options);
        return speechModel.call(prompt).getResult().getOutput();
    }
}
