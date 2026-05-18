# Spring AI Employee Handbook RAG Bot 📘

AI-powered Employee Handbook chatbot built using Spring AI, Ollama, PostgreSQL, and PGVector.

This project demonstrates a complete Retrieval-Augmented Generation (RAG) pipeline where a PDF document is ingested, converted into vector embeddings, stored in PGVector, and used to answer employee policy-related questions semantically.

---

# 🚀 Features

- PDF document ingestion
- Semantic search using vector embeddings
- Retrieval-Augmented Generation (RAG)
- AI-powered question answering
- Local LLM support using Ollama
- PostgreSQL vector database integration
- Spring AI integration
- Metadata-aware retrieval pipeline

---

# 🧠 How It Works

The application follows a standard RAG architecture:

```text
PDF Document
     ↓
TikaDocumentReader
     ↓
TokenTextSplitter
     ↓
Embeddings (nomic-embed-text)
     ↓
PGVector Storage
     ↓
Similarity Search
     ↓
LLM Context Injection
     ↓
Generated AI Response
```

When a user asks a question like:

```text
Can I work from home on Monday?
```

The system:
1. Retrieves relevant chunks from the employee handbook
2. Sends them as context to the LLM
3. Generates a grounded answer

Example response:

```text
No, remote work is allowed only on Fridays.
```

---

# 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring AI
- Ollama
- PostgreSQL
- PGVector
- Apache Tika
- Maven
- Docker

---

# 📂 Project Structure

```text
src/main/java/com/kannan/the_employee_handbook
│
├── controller
│   └── PolicyController.java
│
├── service
│   ├── PolicyLoader.java
│   └── PolicyChatService.java
│
└── TheEmployeeHandbookApplication.java
```

---

# ⚙️ Setup Instructions

## 1️⃣ Clone Repository

```bash
git clone https://github.com/kanan-v/spring-ai-the-employee-handbook.git
cd spring-ai-the-employee-handbook
```

---

## 2️⃣ Start PostgreSQL + PGVector

```bash
docker run --name handbook-db \
-e POSTGRES_USER=my-user \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=handbook_db \
-p 5435:5432 \
-d pgvector/pgvector:0.8.1-pg18-trixie
```

---

## 3️⃣ Start Ollama

Install Ollama:

https://ollama.com/

Start Ollama server:

```bash
ollama serve
```

Pull required models:

```bash
ollama pull llama3
ollama pull nomic-embed-text
```

---

## 4️⃣ Configure Application

Update `application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5435/handbook_db
    username: my-user
    password: password

  ai:
    ollama:
      base-url: http://localhost:11434

      chat:
        model: llama3

      embedding:
        model: nomic-embed-text

    vectorstore:
      pgvector:
        initialize-schema: true
```

---

## 5️⃣ Add PDF

Place `policy.pdf` inside:

```text
src/main/resources/
```

---

## 6️⃣ Run Application

```bash
./mvnw spring-boot:run
```

On startup:
- PDF is read
- Split into chunks
- Converted into embeddings
- Stored in PGVector

---

# 🎯 Example API

## Request

```http
GET /ask?question=Can I work from home on Monday?
```

---

## Example Response

```text
No, remote work is allowed only on Fridays.
```

---

# 🔍 Example Questions

| User Question | Expected Answer |
|---|---|
| Can I work remotely on Monday? | No, only on Fridays |
| What is the expense reimbursement limit? | $50 |
| What are office timings? | 9 AM to 6 PM |

---

# 📊 Concepts Demonstrated

- Retrieval-Augmented Generation (RAG)
- Vector Databases
- Embeddings
- Semantic Search
- Similarity Search
- Document Chunking
- AI Context Retrieval
- Spring AI Integration
- Local LLM Inference

---

# 🧪 Future Improvements

- Multi-document support
- Source citations
- Conversation memory
- Frontend chat UI
- Hybrid search
- Authentication
- Streaming responses

---

# 👨‍💻 Author

Kannan V

---

# ⭐ If you found this useful

Give this repository a star ⭐
