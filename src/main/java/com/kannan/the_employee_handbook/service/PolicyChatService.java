package com.kannan.the_employee_handbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyChatService {

    private final VectorStore vectorStore;
    private final ChatClient.Builder chatClientBuilder;

    public String askQuestion(String question){
        List<Document> documents = vectorStore
                .similaritySearch(
                        SearchRequest.builder()
                                .query(question)
                                .topK(3)
                                .build()
                );
        String context = documents.stream()
                .map(Document::getText)
                .reduce("",(a,b)-> a +"\n"+ b);

        String prompt = """
                You are an employee handbook assistant.

                Answer ONLY using the provided context.

                Context:
                %s

                Question:
                %s
                """.formatted(context, question);

        return chatClientBuilder.build()
                .prompt(prompt)
                .call()
                .content();
    }

}
