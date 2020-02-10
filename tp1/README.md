# TP Spring Boot

## Objectifs pédagogiques

- Utiliser les bonnes pratiques de gestion de projet vues dans les UEs précédentes
- Mettre en place une API s'exécutant dans un framework côté serveur : [Spring](https://spring.io)
- Maîtriser l'utilisation d'annotations Spring et REST en Java

Dans ce TP, vous allez mettre en place une partie du backend d'une application Web mobile : la gestion des utilisateurs à l'aide du framework Spring et de se composants Sprimg Web (pour la partie Web MVC et REST) et Spring Boot (pour faciliter la configuration et le déploiement).

## Gestion de projet

### Projet forge

Créez un projet sur la forge, avec les caractéristiques habituelles (notamment le `.gitignore`). Vous travaillerez en suivant la méthode qui vous correspond le mieux, mais au final, **seules seront évaluées les fonctionnalités poussées sur la branche `master`**.

&Avrave; la fin de chaque TP, vous tagguerez le dernier commit avec le numéro du TP.

### Intégration continue

TODO.

## Infrastructure

&Agrave; l'image de ce que vous avez fait en M1IF03, vous mettrez en place une infrastructure de production sur une VM OpenStack qui vous sera attribuée par vos enseignants. Vous déploierez plusieurs modules sur cette infrastructure :

- nginx (port 80) : serveur de fichiers statiques + reverse proxy
- Spring Boot (Tomcat sur port 8080) : gestion des utilisateurs
- Node Express (port 3000) : fonctionnalités côté serveur spécifiques au métier de l'application

**Remarque** : votre proxy permettra uniquement d'accéder à Express, mais pas à Tomcat. Tomcat jouera le rôle d'un serveur d'authentification centralisé (CAS) indépendant de l'application, comme celui l'UCBL.

### Mise en place de la VM

Installez pour l'instant nginx, sans le configurer en reverse proxy (cela sera fait au TP3).

Déployez une page Web et des ressources à la racine du serveur et vérifiez que vous y accédez correctement.

Mettez en place un test de vérification du fonctionnement d'nginx dans GitLab CI.

## Mise en place d'une application Spring Boot

### Initialisation du projet

Créez une nouvelle application Spring Boot à l'aide de https://start.spring.io/ en y intégrant le module Web.

Buildez, exécutez et testez cette application (*cf*. cours). Vous devez voir une page d'erreur sur le port 8080 de votre machine locale.

&Al'aide du goal `mvn install`, créez un jar de votre application et copiez-le sur votre VM. Lancez le jar sur votre VM et testez que le serveur se lance de la même façon.

Mettez en place un test de vérification du fonctionnement d'nginx dans GitLab CI.

## Conception et déploiement de l'application

Dans votre application, créez une classe User. Comme pour M1IF03, pour vous éviter d'avoir à gérer une BD, vous stockerez les instances de cette classe dans une Map et placerez cette Map dans le contexte applicatif. 

### REST

En vous inspirant de [ce tuto](https://spring.io/guides/gs/rest-service/), mettez en place un contrôleur REST qui permet d'accéder à un utilisateur.

Testez.

Une fois la méthode GET réalisée, améliorez-le pour qu'il réponde à cette interface OpenAPI (**TODO**).

Testez et déployez sur votre VM.

Ajoutez un test dans le pipeline Gitlab qui crée un utilisateur et le récupère.

### Traitement de requêtes "simples"

TODO (login / logout)

TODO : test

### Négociation de contenus

TODO