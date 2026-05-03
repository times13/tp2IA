package ht.fds.mbds.alfred;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.time.Duration;

public class Test4 {

    public static void main(String[] args) {

        GoogleAiEmbeddingModel embeddingModel = GoogleAiEmbeddingModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-embedding-001")
                .taskType(GoogleAiEmbeddingModel.TaskType.SEMANTIC_SIMILARITY)
                .outputDimensionality(300)
                .timeout(Duration.ofSeconds(10))
                .build();

        // Couples de phrases à comparer
        String[][] couples = {
                {"Le chat dort sur le canapé", "Le félin sommeille sur le sofa"},
                {"J'aime le football", "Les mathématiques sont difficiles"},
                {"Bonjour, comment allez-vous ?", "Hello, how are you?"},
                {"Il fait beau aujourd'hui", "La voiture est rouge"}
        };

        for (String[] couple : couples) {
            String phrase1 = couple[0];
            String phrase2 = couple[1];

            Response<Embedding> response1 = embeddingModel.embed(phrase1);
            Response<Embedding> response2 = embeddingModel.embed(phrase2);

            Embedding embedding1 = response1.content();
            Embedding embedding2 = response2.content();

            double similarite = CosineSimilarity.between(embedding1, embedding2);

            System.out.println("Phrase 1 : " + phrase1);
            System.out.println("Phrase 2 : " + phrase2);
            System.out.printf("Similarité : %.4f%n%n", similarite);
        }
    }
}