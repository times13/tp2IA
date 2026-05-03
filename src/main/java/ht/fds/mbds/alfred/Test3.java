package ht.fds.mbds.alfred;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.Map;

public class Test3 {

    public static void main(String[] args) {

        GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-2.5-flash-lite")
                .temperature(0.7)
                .build();

        // Création du template avec une variable "texte"
        PromptTemplate template = PromptTemplate.from(
                "Traduis le texte suivant en anglais. Donne uniquement la traduction, sans explication et introduction : {{texte}}"
        );

        // Texte à traduire
        String texteATraduire = "Bonjour, comment allez-vous aujourd'hui ?";

        // Création du prompt avec la valeur de la variable
        Prompt prompt = template.apply(Map.of("texte", texteATraduire));

        // Texte final qui sera envoyé à Gemini
        String texteFinal = prompt.text();
        System.out.println("Texte envoyé à Gemini : " + texteFinal);

        // Envoi à Gemini
        String reponse = model.chat(texteFinal);

        System.out.println("Texte original : " + texteATraduire);
        System.out.println("Traduction     : " + reponse);
    }
}