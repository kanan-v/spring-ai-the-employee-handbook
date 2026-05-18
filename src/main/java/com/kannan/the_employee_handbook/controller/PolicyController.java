package com.kannan.the_employee_handbook.controller;

import com.kannan.the_employee_handbook.service.PolicyChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PolicyController {

    private final PolicyChatService policyChatService;

    @GetMapping("/ask")
    public String ask(
            @RequestParam String question
    ) {

        return policyChatService.askQuestion(question);
    }
}
