package ht.fds.mbds.alfred;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.output.TokenUsage;

public class Test2 {

    public static void main(String[] args) {

        GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-2.5-flash-lite")
                .temperature(0.7)
                .build();

        UserMessage message = UserMessage.from("Quelle est la capitale du Maroc ?");

        ChatResponse response = model.chat(message);

        // Affiche la réponse
        System.out.println("Réponse : " + response.aiMessage().text());

        // Affiche les tokens
        TokenUsage tokenUsage = response.tokenUsage();
        int tokensEntree = tokenUsage.inputTokenCount();
        int tokensSortie = tokenUsage.outputTokenCount();
        System.out.println("Tokens entrée : " + tokensEntree);
        System.out.println("Tokens sortie : " + tokensSortie);

        // Calcul du coût pour gemini-2.5-flash
        // Entrée : 0.30$ par million de tokens
        // Sortie : 2.50$ par million de tokens
        double coutEntree = (tokensEntree / 1_000_000.0) * 0.30;
        double coutSortie = (tokensSortie / 1_000_000.0) * 2.50;
        double coutTotal = coutEntree + coutSortie;

        System.out.printf("Coût entrée  : $%.8f%n", coutEntree);
        System.out.printf("Coût sortie  : $%.8f%n", coutSortie);
        System.out.printf("Coût total   : $%.8f%n", coutTotal);

        // Nombre de requêtes pour 1 dollar
        long nbRequetes = (long)(1.0 / coutTotal);
        System.out.println("Requêtes possibles pour 1$ : " + nbRequetes);
    }
}