# R3.04 : Design Patterns

<img src="img/cloud-dp.png" height="300"/>


[Ressource R2.01 : Développement Orienté Objets](https://gitlab.univ-nantes.fr/iut.info1.dev.objets/dev.objets.ressources)

[Tutoriel IntelliJ](https://gitlab.univ-nantes.fr/iut.info1.dev.objets/dev.objets.tutoriel.intellij.idea)


## Supports de cours

[Introduction](CMs/00-intro.pdf) (07/09/2026)

[Rappels Kotlin](CMs/01-kotlin.rappels.pdf) (07/09/2026)


[Design patterns](CMs/02-design-patterns.pdf) 
(14/09/2026)

[Nouveautés Kotlin](CMs/03-kotlin.nouveau.pdf) 
(14/09/2026)

[Design patterns 2](CMs/04-design-patterns2.pdf) 
(22/09/2026)

[Design patterns 3](CMs/05-design-patterns3.pdf) 
(28/09/2026)

<!-- 
[Nouveautés Kotlin : lamdba-fonctions, etc.](CMs/05-kotlin.nouveau.lambda.pdf) (13/10/2025)
-->


## TDs

[TD n°2 : Fabrique Personne](TDs/td2.pdf)

[TD n°3 : Template method The/cafe & Cargaisons](TDs/td3.pdf)

[TD n°4 : Strategies ou Template methods](TDs/td4.pdf)

## TPs


[TP n°1 : (re-) prise en main de Kotlin = implémentation d'une file chaînée](https://gitlab.univ-nantes.fr/iut.info2.qdev.dp/etudiants/qdev.dp.tp1)

[TP n°2 : implémentation d'une factory (Personne vs. Entreprise) + algorithmes Date de Pâques (Strategies)](https://gitlab.univ-nantes.fr/iut.info2.qdev.dp/etudiants/qdev.dp.tp2)

[TP n°3 : implémentation de template methods (thé ou café + cargaisons)](https://gitlab.univ-nantes.fr/iut.info2.qdev.dp/etudiants/qdev.dp.tp3)

[TP n°4 : strategies ou template methods](https://gitlab.univ-nantes.fr/iut.info2.qdev.dp/etudiants/qdev.dp.tp4)


<!--

[TP n°6 : Composite, delegate et Singleton](https://gitlab.univ-nantes.fr/iut.info2.qdev.dp/2024-2025/qdev.dp.tp6)

[TP n°7 : Seralisation, DAO et Repository](https://gitlab.univ-nantes.fr/iut.info2.qdev.dp/2024-2025/qdev.dp.tp7)

[TP n°8 : tables de hachage](https://gitlab.univ-nantes.fr/iut.info2.qdev.dp/2024-2025/qdev.dp.tp8)

[TP n°9 : DAO](https://gitlab.univ-nantes.fr/iut.info2.qdev.dp/2024-2025/qdev.dp.tp9)

-->

## Retravailler les TPs à la maison

Les projets Gradle de chaque TP embarque une configuration spécifique aux salles machines de l'IUT ; si vous souhaitez retravailler ces TPs sur vos propres machines, il faut réaliser quelques manipulations avant de pouvoir ouvrir correctement le projet sur votre ordinateur personnel

### La configuration du proxy 

Il vous faut commenter la configuration du proxy dans le fichier `gradle.properties` :

	kotlin.code.style=official

	# systemProp.http.proxyHost=srv-proxy-etu-2.iut-nantes.univ-nantes.prive
	# systemProp.http.proxyPort=3128
	# systemProp.https.proxyHost=srv-proxy-etu-2.iut-nantes.univ-nantes.prive
	# systemProp.https.proxyPort=3128
	# systemProp.http.nonProxyHosts=localhost|nexus-proxy.iut-nantes.univ-nantes.prive
	# systemProp.https.nonProxyHosts=localhost|nexus-proxy.iut-nantes.univ-nantes.prive
	systemProp.javax.net.ssl.trustStore=store
	systemProp.javax.net.ssl.trustStorePassword=iutiut
	

### La configuration du depôt Nexus

Avec le SI de l'IUT, nous avons mis en place plusieurs **[dépôts Nexus](https://en.wikipedia.org/wiki/Sonatype_Nexus_Repository)** afin de 
1) servir de cache pour différents dépôts Maven officiels, afin de réduire le temps de chargement des TPs (mais aussi réduire l'empreinte carbone du chargement de toutes les dépendances), 
2) pouvoir vous mettre à disposition des dépendances `.JAR` plus facilement

[https://nexus-proxy.iut-nantes.univ-nantes.prive](https://nexus-proxy.iut-nantes.univ-nantes.prive)

Attention, ce **dépôt Nexus** n'est accessible que depuis le réseau de l'IUT.

> [!CAUTION]
> Actuellement les serveurs nexus ne sont pas accessible depuis le VPN étudiant ; 
> une demande a été faite au SI

Pour fonctionner depuis votre ordinateur personnel, il vous sera nécessaire de  vous connectez à EduVPN pour que votre machine soit vu comme une machine du réseau  : [voir la documentation](https://www.iut-nantes.univ-nantes.fr/etu/wiki/index.php/Connexion_EduVPN.html). 

> Comme indiuqer au tout début de la documentation, pour vous connecter à EduVPN, il faut que vous demandiez l'activation auprès du SI.

Une fois connecté, vous devriez pouvoir accéder au **dépôts Nexus**.



## Références

[https://refactoring.guru/design-patterns](https://refactoring.guru/design-patterns)

[https://github.com/takaakit/uml-diagram-for-kotlin-design-pattern-examples](https://github.com/takaakit/uml-diagram-for-kotlin-design-pattern-examples)

[https://github.com/kelvindev15/Kotlin2PlantUML](https://github.com/kelvindev15/Kotlin2PlantUML)


<!--

## Problème IntelliJ/Maven (2023-2024)

configurer le proxy  pour la VM Java utilisé par Maven : 
dans  `~/.var/app/com.jetbrains.IntelliJ-IDEA-Community/config/JetBrains/IdeaIC2024.2/idea64.vmoptions`
ou`
`~/.var/app/com.jetbrains.IntelliJ-IDEA-Ultimate/config/JetBrains/IntelliJIdea2024.2/idea64.vmoptions`

    -Xmx2048m
    -Dhttps.proxyHost=srv-proxy-etu-2.iut-nantes.univ-nantes.prive
    -Dhttps.proxyPort=3128

Configurer le proxy pour IntelliJ
dans `~/.var/app/com.jetbrains.IntelliJ-IDEA-Community/config/JetBrains/IdeaIC2024.2/options/proxy.settings.xml` 
ou 
`~/.var/app/com.jetbrains.IntelliJ-IDEA-Ultimate/config/JetBrains/IntelliJIdea2024.2/options/proxy.settings.xml`

    <application>
    <component name="HttpConfigurable">
        <option name="USE_HTTP_PROXY" value="true" />
        <option name="PROXY_HOST" value="srv-proxy-etu-2.iut-nantes.univ-nantes.prive" />
        <option name="PROXY_PORT" value="3128" />
    </component>
    </application>

-->