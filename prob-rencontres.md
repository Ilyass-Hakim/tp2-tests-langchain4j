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
