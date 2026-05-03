package ht.fds.mbds.alfred;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class Test1 {

    public static void main(String[] args) {

        GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-2.5-flash-lite")
                .temperature(0.7)
                .build();

        String reponse1 = model.chat("Comment s'appelle le chat de Pierre ?");
        System.out.println(reponse1);

      //  String reponse2 = model.chat("Quel est mon nom ?");
       // System.out.println(reponse2);
    }
}