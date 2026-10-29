package ma.hakim;

import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.model.output.TokenUsage;

public class Test2Couts {

    public static void main(String[] args) {
        String cle = System.getenv("GEMINI_KEY");

        ChatModel modele = GoogleGenAiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.7)
                .build();

        String question = "Quelle est la capitale du Maroc ?";
        ChatResponse reponse = modele.chat(UserMessage.from(question));
        TokenUsage tokens = reponse.tokenUsage();

        System.out.println("Question : " + question);
        System.out.println("Réponse : " + reponse.aiMessage().text());
        System.out.println("Tokens en entrée : " + tokens.inputTokenCount());
        System.out.println("Tokens en sortie : " + tokens.outputTokenCount());
        System.out.println("Total des tokens : " + tokens.totalTokenCount());
    }
}
