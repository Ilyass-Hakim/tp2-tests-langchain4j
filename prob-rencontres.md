Problèmes rencontrés et solutions

1. Choix des versions de LangChain4j
Problème : l'énoncé donne la version 1.22.0 comme exemple et demande la dernière version.
Solution : j'ai trouvé les dernières versions sur le dépôt Maven : langchain4j 1.23.0 et langchain4j-google-genai 1.23.0-beta33.

2. Package de la classe GoogleGenAiChatModel
Problème : je ne connaissais pas l'import de GoogleGenAiChatModel.
Solution : d'après la documentation de LangChain4j, la classe est dans le package dev.langchain4j.model.google.genai.

3. Modèle gemini-2.5-flash refusé (Test 1)
Problème : erreur 404, le modèle gemini-2.5-flash n'est plus disponible pour les nouveaux utilisateurs.
Solution : j'ai utilisé le modèle gemini-3.5-flash-lite, le moins coûteux, que vous avez mentionné dans le cours.

4. Variable manquante dans le PromptTemplate (Test 3)
Problème : erreur "Value for the variable 'texte' is missing" en appelant apply avec une seule valeur.
Solution : avec une seule valeur, LangChain4j la met dans une variable nommée it. J'ai passé une Map qui donne le nom de la variable : Map.of("texte", "...").

5. Avertissement SLF4J au lancement
Problème : message "No SLF4J providers were found" à chaque exécution.
Solution : ce n'est pas une erreur. LangChain4j écrit ses logs avec SLF4J, mais aucune implémentation n'est installée, donc les logs sont ignorés. Il faudra ajouter Logback pour les voir (Test 7).

6. Package du modèle d'embeddings intégré introuvable (Test 4)
Problème : erreur de compilation "package dev.langchain4j.model.embedding.onnx.bgesmallenv15 does not exist".
Solution : le modèle BgeSmallEnV15EmbeddingModel n'est pas dans les dépendances de base de LangChain4j. J'ai ajouté dans pom.xml la dépendance langchain4j-embeddings-bge-small-en-v15, puis rechargé Maven.

7. Timeout du modèle d'embeddings de Google (Test 4)
Problème : avec un timeout de 100 ms, erreur "GenAiIOException: Failed to execute HTTP request", causée par "InterruptedIOException: timeout".
Solution : 100 ms est trop court pour une requête par Internet vers Google (connexion sécurisée, puis calcul de l'embedding). Comme le demande l'énoncé, j'ai augmenté le timeout : Duration.ofSeconds(5).

8. Test 4 ne compile plus après l'ajout de easy-rag (Test 5)
Problème : comme le demande l'énoncé, j'ai mis en commentaire la dépendance du modèle d'embeddings intégré pour éviter un conflit avec easy-rag. Erreur de compilation dans Test4Embeddings : "package dev.langchain4j.model.embedding.onnx.bgesmallenv15 does not exist". IntelliJ compile tout le projet avant de lancer un test, donc Test5Rag1 ne pouvait pas s'exécuter.
Solution : easy-rag contient déjà une version quantifiée (plus légère) du même modèle. Dans Test4Embeddings, j'ai remplacé BgeSmallEnV15EmbeddingModel par BgeSmallEnV15QuantizedEmbeddingModel (package dev.langchain4j.model.embedding.onnx.bgesmallenv15q).
