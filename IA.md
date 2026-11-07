Utilisation de l'IA pour le TP 2 (tests avec LangChain4j)

IA utilisée : Claude (Anthropic).


1. Comprendre l'énoncé du TP 2
Problème rencontré : l'énoncé est long et je voulais une vue d'ensemble des 7 tests et de l'application Web, ainsi qu'une explication du code fourni dans la page.
Raison de l'utilisation de l'IA : gagner du temps pour comprendre l'organisation du TP avant de commencer.
Prompt utilisé : "Peux-tu me décrire ce qui est demandé dans le TP 2, son intérêt et les étapes à suivre ? S'il y a du code fourni par le professeur, explique-le-moi."
Réponse de l'IA (extrait) : "LangChain4j est un framework Java qui se place au-dessus des API des LLM. Il s'occupe du JSON, des requêtes et de l'historique. [...] Test 5 — RAG 1 : le RAG (Retrieval-Augmented Generation). On donne au LLM tes propres informations, privées ou récentes, qu'il ne connaît pas."
Ce que j'ai appris : J'ai compris que LangChain4j évite d'écrire le JSON et les requêtes HTTP à la main comme au TP 1, et que le TP va du plus simple (un chat) au plus avancé (RAG et outils).


2. Dépendances Maven et versions
Problème rencontré : choisir les dernières versions de LangChain4j, du connecteur Gemini et des plugins Maven (remarque du professeur sur mon TP précédent).
Raison de l'utilisation de l'IA : je ne savais pas comment trouver la bonne version correspondante pour chaque module.
Prompt utilisé : "Voici la liste des versions disponibles sur le dépôt Maven. Quelle est la dernière version stable à utiliser pour chaque dépendance ?"
Réponse de l'IA (extrait) : "Les plus récentes sont 1.23.0 et 1.23.0-beta33 [...] Dans cette liste, 3.9.0 apparaît après 3.13.0, car le tri compare les caractères un par un."
Ce que j'ai appris : J'ai appris à chercher la dernière version d'une dépendance sur le dépôt Maven, en faisant attention au tri alphabétique (3.9.0 apparaît après 3.16.0) et en ignorant les versions alpha et beta. Les modules de LangChain4j doivent avoir le même numéro de version.

3. Test 2 : coût en tokens
Problème rencontré : récupérer le nombre de tokens et calculer le coût d'une requête.
Raison de l'utilisation de l'IA : je ne connaissais pas la méthode qui retourne un ChatResponse.
Prompt utilisé : "Passons au Test 2 : comment afficher le nombre de tokens utilisés par une requête ?" Puis : "Voici les prix des tokens indiqués dans le cours. Comment calculer le coût de cette requête ?"
Réponse de l'IA (extrait) : "j'utilise chat(UserMessage), une des méthodes demandées par l'énoncé, qui renvoie un ChatResponse complet : le message de l'IA (aiMessage()) et les métadonnées, dont tokenUsage()." Calcul : 8 x 0,30 / 1 000 000 + 9 x 2,50 / 1 000 000 = 0,0000249 $, soit environ 40 161 requêtes pour 1 $.
Ce que j'ai appris : J'ai appris que chat(UserMessage) retourne un ChatResponse qui contient la réponse et le nombre de tokens. Les tokens de sortie coûtent beaucoup plus cher que ceux d'entrée, donc il vaut mieux limiter la taille des réponses.


4. Test 3 : traducteur avec PromptTemplate
Problème rencontré : je ne savais pas comment utiliser PromptTemplate, puis erreur "IllegalArgumentException: Value for the variable 'texte' is missing".
Raison de l'utilisation de l'IA : je ne savais pas quoi écrire et le code de la page ne s'affichait pas.
Prompt utilisé : "Je ne sais pas comment écrire le Test 3 et le code de la page ne s'affiche pas. Écris le code dans notre chat, et explique-le-moi." Puis : "J'obtiens l'erreur Value for the variable 'texte' is missing. D'où vient-elle ?" et "Je n'ai pas bien compris la méthode PromptTemplate.from. Peux-tu me l'expliquer ?"
Réponse de l'IA (extrait) : "Quand on appelle apply avec une seule valeur [...] LangChain4j la met dans une variable qui s'appelle it. [...] Option B : garder {{texte}} et passer une Map pour nommer la variable."
Réponse de l'IA (extrait, sur PromptTemplate.from) : "from est une méthode statique de fabrique : une méthode static de la classe qui crée et renvoie un objet de cette classe."
Ce que j'ai appris : J'ai compris qu'un PromptTemplate est un texte avec des variables {{nom}}, que from() le crée et que apply() remplace les variables. Avec une seule valeur, la variable doit s'appeler it ; sinon il faut passer une Map dont les clés sont les noms des variables.


5. Avertissement SLF4J
Problème rencontré : message "SLF4J(W): No SLF4J providers were found" à chaque exécution.
Raison de l'utilisation de l'IA : je ne savais pas si c'était une erreur.
Prompt utilisé : "Le programme fonctionne, mais l'avertissement SLF4J s'affiche toujours. De quoi s'agit-il ?"
Réponse de l'IA (extrait) : "Ce n'est pas une erreur, c'est un avertissement. [...] SLF4J est une façade [...] Ton projet n'en a pas. SLF4J te prévient donc qu'il utilise un logger NOP : tous les logs de LangChain4j sont simplement jetés."
Ce que j'ai appris : J'ai compris que ce message n'est pas une erreur : SLF4J est seulement une façade pour les logs, et il faut ajouter une implémentation comme Logback pour voir les logs de LangChain4j.


6. Test 5 : RAG avec infos.txt
Problème rencontré : compléter le code fourni (clé et modèle), puis erreur de compilation dans Test4Embeddings après avoir mis en commentaire la dépendance du modèle intégré.
Raison de l'utilisation de l'IA : comprendre le code du RAG et l'erreur de compilation.
Prompt utilisé : "Voici le code fourni par le professeur pour le Test 5. Complète-le et explique-le-moi." Puis : "Voici l'erreur de compilation obtenue dans Test4Embeddings. Comment la résoudre ?"
Réponse de l'IA (extrait) : "EmbeddingStoreIngestor.ingest [...] découpe le document en segments, calcule l'embedding de chaque segment, et les range dans la base" ; "easy-rag contient déjà le même modèle, en version quantifiée."
Ce que j'ai appris : J'ai compris les deux phases du RAG : d'abord on découpe le document, on calcule les embeddings et on les range dans une base vectorielle ; ensuite, pour chaque question, on cherche les morceaux les plus proches et on les envoie au LLM avec la question. Les informations du document passent avant celles apprises par le LLM, même si elles sont fausses (Marseille).


7. Test 6 : RAG sur le PDF du cours
Problème rencontré : intégrer le code de conversation fourni et comprendre des réponses incorrectes (ML confondu avec LM).
Raison de l'utilisation de l'IA : comprendre pourquoi les réponses n'étaient pas toujours bonnes.
Prompt utilisé : "Voici le code fourni pour poser plusieurs questions à l'assistant. Intègre-le dans le Test 6." Puis : "Voici la conversation obtenue. Pourquoi certaines réponses sont-elles incorrectes ?"
Réponse de l'IA (extrait) : "Gemini a confondu, parce que le RAG lui a fourni des morceaux qui parlent de modèles de langage [...] le modèle d'embeddings intégré est entraîné en anglais, donc sa similarité est approximative."
Ce que j'ai appris : J'ai compris que le RAG n'est pas infaillible : si les morceaux retrouvés ne sont pas les bons, le LLM répond mal. J'ai aussi vu qu'une mémoire (ChatMemory) permet d'avoir une conversation avec plusieurs questions.


8. Test 7 : outils
Problème rencontré : intégrer MeteoTool, écrire TestMeteo, AssistantMeteo et Test7Outils, écrire l'annotation @Tool de getCoordonnees, comprendre une réponse fausse, une erreur "400 INVALID_ARGUMENT. Please ensure that function call turn comes immediately after a user turn", et lire le logging.
Raison de l'utilisation de l'IA : partie la plus longue du TP, je voulais avancer et comprendre les échanges avec le LLM.
Prompt utilisé : "Passons au Test 7 : guide-moi étape par étape pour utiliser les outils." Puis : "Voici la conversation et l'erreur 400 obtenues. Comment les expliquer ?" et "Voici le logging obtenu. Peux-tu m'aider à comprendre les échanges avec le LLM ?"
Réponse de l'IA (extrait) : "Gemini a appelé l'outil pour demain seulement (nbJoursDebut = 1, nbJours = 1), au lieu de 3 jours" ; "La mémoire supprime alors les plus anciens, dont une de tes questions. L'historique envoyé à Gemini commence donc par une demande d'appel d'outil" ; "Coût total pour une seule question : 438 + 521 + 573 = 1 532 tokens."
Ce que j'ai appris : J'ai compris qu'un outil est une méthode Java annotée avec @Tool : le LLM ne voit que la description, il demande l'appel avec les paramètres qu'il choisit, LangChain4j exécute la méthode et renvoie le résultat au LLM. Le LLM peut choisir de mauvais paramètres. Chaque appel d'outil ajoute des messages à la mémoire et renvoie les descriptions des outils, ce qui coûte beaucoup de tokens.
