package com.example.micromatch.controller;

import com.example.micromatch.service.ChatbotService;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/chatbot")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/query")
    public String getChatbotResponse(@RequestBody String query) {
        return chatbotService.getResponse(query);
    }
}
