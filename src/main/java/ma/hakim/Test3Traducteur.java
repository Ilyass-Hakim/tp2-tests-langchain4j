package ma.hakim;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.google.genai.GoogleGenAiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.Map;

public class Test3Traducteur {

    public static void main(String[] args) {
        String cle = System.getenv("GEMINI_KEY");

        ChatModel modele = GoogleGenAiChatModel.builder()
                .apiKey(cle)
                .modelName("gemini-3.5-flash-lite")
                .temperature(0.7)
                .build();

        PromptTemplate template1 = PromptTemplate.from("Traduis le texte suivant en anglais : {{texte}}");
        Prompt prompt1 = template1.apply(Map.of("texte", "Bonjour, comment vas-tu ?"));
        System.out.println("Prompt : " + prompt1.text());
        System.out.println("Réponse : " + modele.chat(prompt1.text()));
        System.out.println("----------");

        PromptTemplate template2 = PromptTemplate.from("Traduis le texte suivant en {{langue}} : {{texte}}");
        Prompt prompt2 = template2.apply(Map.of(
                "langue", "espagnol",
                "texte", "J'apprends à utiliser LangChain4j avec Java."));
        System.out.println("Prompt : " + prompt2.text());
        System.out.println("Réponse : " + modele.chat(prompt2.text()));
    }
}
