package com.example.springaidevelopment.audiocontrollers;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/ai/v1")
public class SpeechToTextGenController {
    private OpenAiAudioTranscriptionModel audioTranscriptionModel;
    public SpeechToTextGenController(OpenAiAudioTranscriptionModel audioTranscriptionModel) {
        this.audioTranscriptionModel = audioTranscriptionModel;
    }

    @PostMapping("/speech/stt")
    public String getTranscriptionText(@RequestParam MultipartFile file) {
        return audioTranscriptionModel.call(file.getResource());
    }

    @PostMapping("/speech/stt/options")
    public String getTranscriptionTextWithOptions(@RequestParam MultipartFile file) {
        OpenAiAudioTranscriptionOptions audioTranscriptionOptions = OpenAiAudioTranscriptionOptions.builder()
                .language("en")
                .temperature(0.0f)
//                This property just below ensures that transcription has timestamp.
                .responseFormat(OpenAiAudioApi.TranscriptResponseFormat.VTT)
                .build();
        AudioTranscriptionPrompt audioTranscriptionPrompt = new AudioTranscriptionPrompt(
                file.getResource(), audioTranscriptionOptions
        );

        return audioTranscriptionModel.call(audioTranscriptionPrompt).getResult().getOutput();
    }


}
