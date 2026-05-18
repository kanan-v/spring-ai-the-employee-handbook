package com.kannan.the_employee_handbook.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyLoader {

    private final VectorStore vectorStore;

    @PostConstruct
    public void loadPdf(){
        TikaDocumentReader reader = new TikaDocumentReader(
                new ClassPathResource("policy.pdf")
        );

        List<Document> documents = reader.get();
        TokenTextSplitter splitter = TokenTextSplitter.builder()
                .withChunkSize(300)
                .withMinChunkSizeChars(100)
                .withMinChunkLengthToEmbed(5)
                .withMaxNumChunks(10000)
                .withKeepSeparator(true)
                .build();
        List<Document>splitDocs = splitter.apply(documents);
        vectorStore.add(splitDocs);
        System.out.println(splitDocs);
        System.out.println("Policy PDF loaded successfully!");
    }



}
