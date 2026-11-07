package ma.hakim;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.bgesmallenv15q.BgeSmallEnV15QuantizedEmbeddingModel;
import dev.langchain4j.model.google.genai.GoogleGenAiEmbeddingModel;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.time.Duration;

public class Test4Embeddings {

    private static final String[][] COUPLES = {
            {"Le chat dort sur le canapé.", "Un chat fait la sieste sur le sofa."},
            {"Il fait très chaud aujourd'hui.", "Il fait très froid aujourd'hui."},
            {"J'aime programmer en Java.", "J'adore écrire du code Java."},
            {"La capitale du Maroc est Rabat.", "Mon frère joue au football."},
            {"Le train est en retard.", "Le train est arrivé à l'heure."},
            {"Comment vas-tu ?", "Comment ça va ?"}
    };

    public static void main(String[] args) {
        EmbeddingModel modeleIntegre = new BgeSmallEnV15QuantizedEmbeddingModel();

        EmbeddingModel modeleGoogle = GoogleGenAiEmbeddingModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-embedding-001")
                .taskType(GoogleGenAiEmbeddingModel.TaskTypeEnum.SEMANTIC_SIMILARITY)
                .timeout(Duration.ofSeconds(5))
                .build();

        System.out.println("=== Modèle intégré (BgeSmallEnV15 quantifié) ===");
        afficherSimilarites(modeleIntegre);

        System.out.println("=== Modèle Google (gemini-embedding-001) ===");
        afficherSimilarites(modeleGoogle);
    }

    private static void afficherSimilarites(EmbeddingModel modele) {
        for (String[] couple : COUPLES) {
            Embedding embedding1 = modele.embed(couple[0]).content();
            Embedding embedding2 = modele.embed(couple[1]).content();
            double similarite = CosineSimilarity.between(embedding1, embedding2);
            System.out.printf("%.4f : \"%s\" / \"%s\"%n", similarite, couple[0], couple[1]);
        }
    }
}
