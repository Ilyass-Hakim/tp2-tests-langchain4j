package ma.hakim;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;

public class Test1 {

    public static void main(String[] args) {
        String cle = System.getenv("GEMINI_KEY");

        ChatModel modele = GoogleGenAiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.7)
                .build();

        poser(modele, "Quelle est la capitale du Maroc ?");

        poser(modele, "Quelle heure est-il ?");

        poser(modele, "Bonjour, je m'appelle Ilyass Hakim.");
        poser(modele, "Comment est-ce que je m'appelle ?");
    }

    private static void poser(ChatModel modele, String question) {
        String reponse = modele.chat(question);
        System.out.println("Question : " + question);
        System.out.println("Réponse : " + reponse);
        System.out.println("----------");
    }
}
