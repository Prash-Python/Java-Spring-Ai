//package com.example.springaidevelopment.ollamacontrollers;
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.chat.metadata.ChatResponseMetadata;
//import org.springframework.ai.chat.model.ChatResponse;
//import org.springframework.ai.ollama.OllamaChatModel;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/ollama/api/ai/v1")
//@Primary
//public class OllamaChatClientController {
//    /** Ollama is open source Free LLM that is installed locally and has many models like mistral, deepseek etc.
//     * Mistral is the default with Spring-AI when working with Ollama model. */
//    private ChatClient chatClient;
//    public OllamaChatClientController(OllamaChatModel ollamaChatModel) {
//        this.chatClient = ChatClient.create(ollamaChatModel);
//    }
//
//    @GetMapping("/answer/{prompt}")
//    public ResponseEntity<String> getResponseText(@PathVariable String prompt) {
//        ChatResponse chatResponse = chatClient
//                .prompt(prompt)
//                .call()
//                .chatResponse();
//        System.out.println("Chat Response From Ollama: " + chatResponse);
//        ChatResponseMetadata chatResponseMetadata = chatResponse.getMetadata();
//        System.out.println("Chat Response Metadata: " + chatResponseMetadata);
//        String modelFromMetadata = chatResponseMetadata.getModel();
//        System.out.println("Model From Metadata: " + modelFromMetadata);
//        return ResponseEntity.ok(chatResponse
//                .getResult()
//                .getOutput()
//                .getText());
//    }
//}
