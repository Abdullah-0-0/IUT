# De la conception à l'ingénierie du prompt

Le \*\***prompt**\*\* désigne la requête exprimée en langage naturel et envoyée à un modèle de langage pour recevoir une réponse.  
Le \*\***prompt design**\*\* (conception de requêtes) consiste à élaborer des instructions claires permettant d'obtenir des réponses précises et de haute qualité, tandis que le \*\***prompt engineering**\*\* représente le processus itératif de mise à jour répétée et d'évaluation de ces requêtes pour optimiser les performances du modèle.

L'efficacité d'une requête repose fondamentalement sur deux piliers :   
\- son \*\***contenu**\*\* (toutes les informations pertinentes nécessaires à l'exécution de la tâche)   
\- et sa \*\***structure**\*\* (la manière de formuler l'information, son organisation et ordonnancement, l'utilisation de délimiteurs). 

# \#\# Exercice 1 : Anatomie simplifiée d'un prompt

Différents cadres de conception d'un prompt ont été proposés parmi lesquels le cadre \*\***RACE**\*\* (\_Role, Action, Context, Expect\_) est l'un des plus simples et plus rapides à acquérir.   
Il les définit les sections suivantes dans un prompt : 

* \*\***ROLE**\*\* (aussi appelée \*\*Persona\*\* ou \*\*System Instruction\*\*)  \*\*\[Facultatif\]\*\*: Définition de l'identité, de l'expertise, de la raison d'être ou de la posture adoptée par le modèle. Par raison d'être, on désigne l'intention (\*\*Intent\*\*) ou motivation (\*\*Purpose\*\*) du système. Par posture, on entend les \*\*contraintes\*\*, ou règles objectives, que doivent respecter toutes les réponses que le système peut produire quand une action lui est demandée. (e.g. \*"Tu es un enseignant en informatique avec une expertise en prompt design et prompt engineering avec pour motivation principale d'assister des étudiants en BUT INFO dans l'apprentissage de l'écriture de prompt"\* ou \*"Vous êtes un expert en codage frontend"\*)   
* \*\***ACTION**\*\* (aussi appelée \*\*tâche\*\* ou \*\*instruction\*\*) \*\*\[obligatoire\]\*\* : Texte principal de la requête qui décrit l'action exacte à accomplir ou la question à laquelle le modèle doit répondre (e.g. \*"Générer un exercice pour enseigner l'art du prompt"\*, \*"Analyser"\*).   
* \*\***CONTEXT**\*\* \*\*\[Facultatif\]\*\* : Données d'arrière-plan ou documents de référence que le modèle doit traiter pour produire sa réponse ou pour ancrer sa réponse dans des faits précis et réduire les risques d'hallucination.  
* \*\***EXPECT**\*\* : Spécification de qui est attendu, notamment le format de la réponse (e.g. "trois phrases") et la structure de sortie attendue (eg. JSON, tableau Markdown, liste à puces, paragraphes en prose)  

RACE présente l'avantage de présenter un cadre de compréhension général de ce qu'est un prompt. Chacun de ses composants peut en effet être étendu. 

Ci-dessous un exemple de prompt. Reconnaissez-vous les différents composants ?

*Tu es un agent d'une agence de voyage spécialisé dans les voyages insolites et hors des sentiers battus. Le client souhaite découvrir l'Islande pendant 7 jours avec un budget de 2 000 €. Propose un voyage insolite adapté à ses envies, à son budget et à la durée du séjour. Présente une destination, trois expériences originales et une estimation du budget total.*

## \#\#\# \*\*Consignes à suivre\*\* :

Dans cet exercice, utilisez \[Vibe de Mistral\]([https://chat.mistral.ai/work](https://chat.mistral.ai/work)) avec un "Nouveau chat/conversation" systématiquement pour chaque nouvelle requête. 

Soit la requête suivante : \`Analyse les modèles de langue et rédige un rapport.\`

### 1\. Requête brute

Soumettez la requête brute initiale à l'agent conversationnel et observez la réponse obtenue. Comment le système répond-il ? Le système répond-il à votre requête ? Comparez avec vos voisins.

### 2\. Requête étendue

Rédigez une requête étendue qui structure les composants ci-dessous. Soumettez la requête et observez la réponse obtenue. Que notez-vous ?

   \* \`ROLE\` : Précédez la tâche en attribuant au système un rôle ayant les compétences pour la tâche que vous souhaitez réaliser.  
    \* \`TASK\` : Précisez la tâche afin que l'analyse porte sur les modèles les plus récents et observe la taille, la licence, le coût des modèles.   
    \* \`EXPECT\` : Demandez de présenter les caractéristiques des modèles sous forme d'un tableau et de résumer les résultats de l'analyse en quelques phrases.

###   3\. Claire et précise

Si vous avez utilisé des expressions comme "les plus récents", "quelques phrases",  "taille" ou "licence" ou "coût" sans expliciter ce qu'ils couvrent, reprenez votre prompt pour être clair et précis.   
Précisez 

* de travailler une période des 6 derniers mois  
* de résumer les résultats d'analyse en 4 items   
* d’utiliser certaines unités de mesure : les tailles (XS, S, M, L…), le coût de consommation en mWh, les licences (propriétaire, poids ouverts, open source). Pour rappel ce sont les unités utilisés dans le \[classement de ComparIA\]([https://arene.comparia.beta.gouv.fr/ranking\#](https://arene.comparia.beta.gouv.fr/ranking#) ). Ne fournissez pas cette URL au modèle.  


Soumettez la requête et observez la réponse obtenue. Que notez-vous ?   

###   4\. Structure explicite 

La structuration du prompt aide à la compréhension. A minima vous pouvez aller à la ligne pour chaque section du prompt. Vous pouvez aussi délimiter explicitement chaque composant à l'aide de balises XML ou de marquage Markdown (e.g.\`\#OBJECTIVE\_AND\_PERSONA, \#INSTRUCTIONS, \#CONSTRAINTS \#OUTPUT\_FORMAT\`). \*\*\#CONSTRAINTS\*\* est une section qui peut suivre vos instructions pour les préciser avec le détail.   
Modifiez votre requête avec un marquage explicite de la structure. Soumettez la requête et observez la réponse obtenue. Que notez-vous ?

Ci-dessous un exemple de prompt avec une structure explicite :   
\# ROLE   
Tu es un agent d'une agence de voyage spécialisé dans les voyages insolites et hors des sentiers battus. 

\# CONTEXT   
Le client souhaite découvrir l'Islande pendant 7 jours avec un budget de 2 000 €. 

\# TASK   
Propose un voyage insolite adapté à ses envies, à son budget et à la durée du séjour. 

\# EXPECTED   
Présente une destination, trois expériences originales et une estimation du budget total.

###   5\. Format de sortie contrôlé

Demandez à ce que les caractéristiques des modèles soient présentées sous forme d'une liste d'objet JSON qui respecte un schéma que vous définirez en donnant des noms en anglais pour les attributs et en spécifiant leur définition dans le champ de la valeur (e.g.  \`{ "model": "le nom du modèle", ...\`).   
Evitez les informations contradictoires avec des spécifications dans d'autres sections. Soumettez la requête et observez la réponse obtenue. Que notez-vous ?

Ci-dessous un exemple de schéma pour la description de véhicule automobile   
{ "marque": "La marque française du véhicule automobile",   
"modele": "Modèle du véhicule",   
"annee": "valeur numérique représentant l’année de sortie du modèle",   
"prix":"valeur numérique au format anglais désignant le prix en euro sans préciser l’unité",   
"puissance\_ch": "valeur numérique précisant la puissance du véhicule en chevaux fiscaux sans rappeler l’unité",   
"carburant": "une valeur issue de la liste suivante essence, électrique ou hybride" }      

###   6\. Encore plus de précision et de contrôles

Il est probable que votre modèle génère davantage de caractéristiques que celles que vous souhaitez observer (pour rappel : la taille, la licence et le coût du modèle).  
Révisez votre prompt, en contraignant davantage la requête dans la section "\<CONSTRAINTS\>" (après les instructions). Ordonnez 

- d'utiliser seulement les caractéristiques spécifiées   
- et d’utiliser une valeur "NA" quand il n'a pas l'information ou qu'il n'arrive pas à l'estimer.

Soumettez la requête et observez la réponse obtenue. Que notez-vous ?  
  

# \#\# Exercice 2 : Stratégie Zero-Shot vs Few-Shot Prompting

Le \*\***Zero-Shot Promptin**g\*\* formule une consigne directe sans fournir d'exemple d'illustration. Le prompt de l'exercice précédent était un prompt \*Zero-Shot\*. Le \*\***Few-Shot Prompting**\*\* intègre de 1 à 5 exemples (sample) de paires d'entrées et de sorties dans le corps de la requête. Cette technique permet d'imposer un ton, une concision ou un format de sortie complexe (tel qu'un schéma JSON strict) de manière beaucoup plus constante qu'une simple explication textuelle.

Ci-dessous un exemple possible de composant \`**\# FEW-SHOT SAMPLES**\`. 

\# FEW-SHOT SAMPLES   
Utilise ces exemples pour identifier le format et le type de transformation attendus, puis applique ce même schéma à la nouvelle entrée.

\#\# SAMPLE 1   
\#\#\# INPUT   
Destination : Japon ; Durée : 5 jours ; Budget : 1 500 €   
\#\#\# OUTPUT   
Destination : Japon rural ; Expériences : nuit dans un temple, randonnée dans les montagnes, découverte d'un village traditionnel ; Budget estimé : 1 400 €. 

Les agents connaissent les sections FEW-SHOT SAMPLES, il n’est pas nécessaire de donner une consigne. De même, si vous donnez qu’un seul exemple, SAMPLE 1 n’est pas nécessaire. Si vous omettez l’INPUT, on est très proche d’une section EXPECT ou OUTPUT\_FORMAT.

Vous pouvez utilisez des balises XML à la place du Markdown

    \<SAMPLE\>  
    \<INPUT\>  
    données en entrée  
    \<INPUT\>  
    \<OUTPUT\>  
    données en sortie  
    \</OUTPUT\>  
    \</SAMPLE\>

## \#\#\# \*\*Consignes à suivre\*\* :

Dans cet exercice, utilisez \[Vibe de Mistral\]([https://chat.mistral.ai/work](https://chat.mistral.ai/work) ) ou \[Qwen\]([https://chat.qwen.ai](https://chat.qwen.ai) ) en restant dans la même conversation. 

### \#\#\#\# 1\.   First shot

Vous souhaitez vous faire assister par un agent pour formater des données d'un format d'entrée à un certain format de sortie. Les données ne sont pas connues au moment de l'écriture du prompt ; vous supposerez n'en posséder que quelques exemples.

Rédigez un prompt avec les sections “ROLE", "TASK" "FEW-SHOT SAMPLES" et "OUTPUT FORMAT". Utilisez la technique few-shot pour illustrer le format d'entrée et spécifier le format de sortie souhaité.

Ci-dessous une base de connaissances (issue de ComparIA) relative à des modèles de langue de 2026\. Les lignes suivent le même format et décrivent chacune un modèle. Le format est le suivant : valeur1 (unité ou type de la valeur1) valeur2 (unité ou type de la valeur2) valeur3 (unité ou type de la valeur3)...  
Piochez dedans (1 à 5 exemples) pour illustrer le format d'entrée puis ensuite pour tester votre prompt.

Imaginez le format de sortie de votre choix. Par exemple : une sortie Markdown ou JSON en changeant l'ordre des informations. Vous pouvez aussi supprimer ou ajouter des colonnes avec des contenus qui n'existent pas comme un commentaire que vous imaginerez pour votre exemple ("modèle le plus coûteux").  
Par exemple : |valeur3|valeur2|valeur1|modèle très coûteux|   
Ici les unités ont été retirés, les valeurs ont été encadrées par \`|\`, présentées dans un ordre différent de celui d’une ligne, et un commentaire libre a été ajouté.

Soumettez votre requête dans un premier prompt puis fournissez des données à transcrire dans un second prompt.

\<KNOWLEDGE\_BASE\>  
    *muse-spark-1-2    (Model)    1208    (BT score)    N/A    (mWh per 1000 tokens)    XL    (Size)    08/2026    (Release)     Meta    (Provider)     proprietary     (License)*  
    *muse-spark-1-3    (Model)    1142    (BT score)    N/A    (mWh per 1000 tokens)    XL    (Size)    09/2026    (Release)     Meta    (Provider)     proprietary     (License)*  
    *gpt-5.3    (Model)    1137    (BT score)    N/A    (mWh per 1000 tokens)    L    (Size)    03/2026    (Release)     OpenAI    (Provider)     proprietary     (License)*  
    *gpt-5.6-terra    (Model)    1127    (BT score)    N/A    (mWh per 1000 tokens)    L    (Size)    07/2026    (Release)     OpenAI    (Provider)     proprietary     (License)*  
    *gemini-3.1-flash-lite-preview    (Model)    1110    (BT score)    N/A    (mWh per 1000 tokens)    L    (Size)    03/2026    (Release)     Google    (Provider)     proprietary     (License)*  
    *gpt-5.5    (Model)    1109    (BT score)    N/A    (mWh per 1000 tokens)    L    (Size)    04/2026    (Release)     OpenAI    (Provider)     proprietary     (License)*  
    *gemini-3.5-flash-lite    (Model)    1108    (BT score)    N/A    (mWh per 1000 tokens)    L    (Size)    07/2026    (Release)     Google    (Provider)     proprietary     (License)*  
    *gpt-5.4    (Model)    1101    (BT score)    N/A    (mWh per 1000 tokens)    L    (Size)    03/2026    (Release)     OpenAI    (Provider)     proprietary     (License)*  
    *gpt-5.6-luna    (Model)    1100    (BT score)    N/A    (mWh per 1000 tokens)    L    (Size)    07/2026    (Release)     OpenAI    (Provider)     proprietary     (License)*  
    *gemma-4-31b-it    (Model)    1100    (BT score)    117    (mWh per 1000 tokens)    S    (Size)    04/2026    (Release)     Google    (Provider)     open-weights     (License)*  
    *gpt-5.4-mini    (Model)    1091    (BT score)    N/A    (mWh per 1000 tokens)    S    (Size)    03/2026    (Release)     OpenAI    (Provider)     proprietary     (License)*  
    *gemma-4-26b-a4b-it    (Model)    1086    (BT score)    84    (mWh per 1000 tokens)    S    (Size)    04/2026    (Release)     Google    (Provider)     open-weights     (License)*  
    *minimax-m3    (Model)    1081    (BT score)    1718    (mWh per 1000 tokens)    XL    (Size)    06/2026    (Release)     MiniMax    (Provider)     open-weights     (License)*  
    *gemini-3.1-pro-preview    (Model)    1070    (BT score)    N/A    (mWh per 1000 tokens)    XL    (Size)    02/2026    (Release)     Google    (Provider)     proprietary     (License)*  
    *gemini-3.5-flash    (Model)    1069    (BT score)    N/A    (mWh per 1000 tokens)    XL    (Size)    05/2026    (Release)     Google    (Provider)     proprietary     (License)*  
    *qwen3-coder-next    (Model)    1067    (BT score)    332    (mWh per 1000 tokens)    M    (Size)    02/2026    (Release)     Qwen    (Provider)     open-weights     (License)*  
    *deepseek-v4-flash    (Model)    1063    (BT score)    1524    (mWh per 1000 tokens)    L    (Size)    04/2026    (Release)     DeepSeek    (Provider)     open-weights     (License)*  
    *gpt-5.4-nano    (Model)    1053    (BT score)    N/A    (mWh per 1000 tokens)    XS    (Size)    03/2026    (Release)     OpenAI    (Provider)     proprietary     (License)*  
    *trinity-large-preview    (Model)    1050    (BT score)    1524    (mWh per 1000 tokens)    L    (Size)    01/2026    (Release)     Arcee    (Provider)     open-weights     (License)*  
    *deepseek-v4-pro    (Model)    1047    (BT score)    8890    (mWh per 1000 tokens)    XL    (Size)    04/2026    (Release)     DeepSeek    (Provider)     open-weights     (License)*  
    *mistral-small-2603    (Model)    1025    (BT score)    347    (mWh per 1000 tokens)    L    (Size)    03/2026    (Release)     Mistral AI    (Provider)     open-weights     (License)*  
    *trinity-large-thinking    (Model)    1025    (BT score)    1524    (mWh per 1000 tokens)    L    (Size)    04/2026    (Release)     Arcee    (Provider)     open-weights     (License)*  
    *kimi-k2.6    (Model)    1024    (BT score)    3785    (mWh per 1000 tokens)    XL    (Size)    04/2026    (Release)     Moonshot AI    (Provider)     open-weights     (License)*  
    *minimax-m2.5    (Model)    995    (BT score)    733    (mWh per 1000 tokens)    L    (Size)    02/2026    (Release)     MiniMax    (Provider)     open-weights     (License)*  
    *qwen3.6-plus    (Model)    985    (BT score)    N/A    (mWh per 1000 tokens)    XL    (Size)    04/2026    (Release)     Qwen    (Provider)     proprietary     (License)*  
    *lfm2-24b-a2b    (Model)    970    (BT score)    82    (mWh per 1000 tokens)    S    (Size)    02/2026    (Release)     Liquid    (Provider)     open-weights     (License)*  
    *minimax-m2.7    (Model)    964    (BT score)    733    (mWh per 1000 tokens)    L    (Size)    03/2026    (Release)     MiniMax    (Provider)     open-weights     (License)*  
    *qwen3.7-max    (Model)    959    (BT score)    N/A    (mWh per 1000 tokens)    XL    (Size)    05/2026    (Release)     Qwen    (Provider)     proprietary     (License)*  
    *glm-5.1    (Model)    951    (BT score)    4095    (mWh per 1000 tokens)    XL    (Size)    04/2026    (Release)     Zhipu    (Provider)     open-weights     (License)*  
    *granite-4.1-8b    (Model)    951    (BT score)    89    (mWh per 1000 tokens)    XS    (Size)    04/2026    (Release)     IBM    (Provider)     open-weights     (License)*  
    *qwen3.5-397b-a17b    (Model)    946    (BT score)    1601    (mWh per 1000 tokens)    L    (Size)    02/2026    (Release)     Qwen    (Provider)     open-weights     (License)*  
    *nemotron-3-super-120b-a12b    (Model)    939    (BT score)    376    (mWh per 1000 tokens)    L    (Size)    02/2026    (Release)     Nvidia    (Provider)     open-source     (License)*  
    *glm-5    (Model)    938    (BT score)    4095    (mWh per 1000 tokens)    XL    (Size)    02/2026    (Release)     Zhipu    (Provider)     open-weights     (License)*  
    *qwen3.5-35b-a3b    (Model)    902    (BT score)    166    (mWh per 1000 tokens)    S    (Size)    02/2026    (Release)     Qwen    (Provider)     open-weights     (License)*

\</KNOWLEDGE\_BASE\>

### \#\#\#\# 2\.   Second shot

Vous pouvez imaginer des formats de sorties compliqués et testés si le nombre d'exemples jouent sur le respect des consignes.

# \#\# Exercice 3 : Le rôle du contexte : Définition des données à traiter et contrôle des hallucinations

La définition d'une section \*\*contexte\*\* (\`\#CONTEXT\`) permet de partager avec le modèle des données que l'on souhaite que il traite spécifiquement ou qu'il utilise pour répondre à des requêtes. Cela permet de garantir l'exactitude des réponses et d'empêcher le modèle d'inventer des faits (\*hallucinations\*). 

## \#\#\# \*\*Consignes à suivre\*\* :

Dans cet exercice, nous allons utiliser des modèles de Claude https://claude.ai/chat en particulier deux modèles :  
\- un modèle qui n'est pas conçu pour raisonner : Haiku 4.5, \_le plus rapide pour des réponses rapides\_ (attention à bien désactiver l'\_Effort\_)   
\- un modèle qui est conçu pour raisonner : Sonnet 5, \_le plus efficace pour des tâches quotidiennes\_ (on optera pour un effort Elevé). 

Vous utiliserez de nouvelles conversations pour chacun des prompts.

Pour réduire les coûts d'utilisation, nous vous invitons à travailler en binôme. Cela vous permettra aussi d'avoir plus de crédits d'utilisation. L’un d’entre vous utilisera un modèle et l’autre l’autre modèle.

Les prompts que vous rédigerez porteront sur la tâche suivante :  
déterminer le modèle qui minimise le coût (mWh), maximise la satisfaction (BT) tout en étant parmi les plus récents et open source ou à poids ouverts, et qui ne soit pas de Google.

### \#\#\#\# 1\. Connaissance des modèles 

Interrogez chacun des modèles et observez leur réponse. 

### \#\#\#\# 2\. Base contrôlée

- A la main, identifiez dans la base de connaissances de l'exercice 2, les modèles qui vérifient les contraintes. Plusieurs réponses seront possibles en fonction de la date… 

- Demandez maintenant aux modèles de répondre à votre problématique en fournissant dans une section CONTEXTE la base de connaissance de l'exercice 2\. Observez leur réponse, notamment par rapport à ce que vous avez observé.

# 

# \#\# Exercice 4 : Raisonnement pas à pas (Chain-of-Thought) et Chaînage de prompts

Pour les tâches nécessitant de la logique, de l'arithmétique ou de la résolution de problèmes complexes, inciter le modèle à expliciter son raisonnement étape par étape (\*Chain-of-Thought\*) améliore significativement l'exactitude du résultat. De plus, lorsqu'une tâche demande d'effectuer trop d'opérations cognitives distinctes en une seule requête, il convient de la décomposer en une séquence de requêtes distinctes (\*prompt chaining\*), où la sortie d'un prompt devient l'entrée du suivant.

La technique de Chain-of-Thought (CoT) peut inviter explicitement le modèle à raisonner ("Raisonne pas à pas avant de produire ta réponse") ou bien elle peut détailler les étapes à réaliser avant de produire la réponse. 

## \#\#\# \*\*Consignes à suivre\*\* :

Dans cet exercice, nous allons utiliser des modèles de Claude https://claude.ai/chat en particulier deux modèles :  
\- un modèle qui n'est pas conçu pour raisonner : Haiku 4.5, \_le plus rapide pour des réponses rapides\_ (attention à bien désactiver l'\_Effort\_)   
\- un modèle qui est conçu pour raisonner : Sonnet 5, \_le plus efficace pour des tâches quotidiennes\_ (on optera pour un effort Elevé). 

Vous utiliserez de nouvelles conversations pour chacun des prompts.

Pour réduire les coûts d'utilisation, nous vous invitons à travailler en binôme. Cela vous permettra aussi d'avoir plus de crédits d'utilisation. Un modèle chacun.

Le problème sur lequel nous travaillerons visera à   
déterminer le modèle qui minimise le coût (mWh), maximise la satisfaction (BT) tout en étant parmi les plus récents et open source ou à poids ouverts, et qui ne soit pas de Google.

### \#\#\#\# 1\. Inviter à raisonner

Révisez votre prompt pour inviter le modèle avec le moins de capacité de raisonnement à raisonner. L'invitation à raisonner et la méthode de raisonnement est à placer dans la section qui décrit la tâche.   
Vous pouvez par exemple dans un premier temps clarifier les contraintes du sujets puis indiquer la méthode de raisonnement comme par exemple : "1. filtrer le tableau selon les critères d'exclusion (fournisseur, licence) et lister les modèles restants".

Vous pouvez aussi interroger le modèle avec la capacité de raisonnement la plus grande de vous produire un prompt qui invite à réfléchir pour répondre à la question posée...

Testez. Est-ce que l'invite conduit le modèle le plus faible à produire le même résultat que le plus fort ?

### \#\#\#\# 2\. Chaînage de prompts

Avec le modèle plus faible, dans une nouvelle conversation, créer un agent qui vise à vous assister dans la résolution de problèmes et de questions de recherche d’information sur votre jeu de données (imaginez le rôle, et les autres sections qui vont bien). Puis, dans des prompts distincts qui reprennent les étapes de votre méthodologie, guider le système pour répondre à la problématique des questions précédentes. Obtenez-vous les mêmes résultats ?

# 

# \#\# Exercice 5\. : les instructions systèmes v. les instructions utilisateurs

Pour cet exercice, utilisez Vibe ou Qwen préférentiellement.

Le \*\*rôle\*\* est un aspect de la spécification du comportement du système. Il est possible de surcroît de définir la raison d'être du modèle c'est-à-dire son intention (\*\*Intent\*\*) ou motivation (\*\*Purpose\*\*). Il est aussi possibles de spécifier \*\*contraintes\*\*, ou règles objectives, que doivent respecter toutes les réponses que le système peut produire quand une action lui est demandée. On peut couvrir toutes ces informations sous l'étiquette \`\#SYSTEM INSTRUCTION\`.  
En opposition, la tâche désignera l'instruction demandée par un utilisateur. 

## \#\#\# \*\*Consignes à suivre\*\* :

Soit le prompt suivant

    \# SYSTEM INSTRUCTION  
    Tu es un expert dans les modèles de langue sortis en 2026 et souhaite assister toute personne désireuse de se renseigner sur les modèles.  
    \# TASK  
    Indiquez le modèle le plus populaire en 2023\.

### \#\#\#\# 1\. Requête brute  

Soumettez la requête et observez si le modèle réalise effectivement la tâche.

### \#\#\#\# 2\. Instructions systèmes

Modifiez les instructions au système pour que la tâche demandée ne soit pas réalisée. Soumettez la requête et observez la réponse obtenue. Que notez-vous ? 

### \#\#\#\# 3\. Les connaissances font la différence ?

Si vos instructions systèmes réussissent à entraver la tâche, alors modifiez la tâche pour explicitement demander au modèle d'exploiter ses connaissances comme ci-dessous. Vos instructions systèmes sont-elles assez robustes ?   
