Voici un `README.md` fusionné en combinant les deux versions du projet avec toutes les sections nécessaires et bien structurées.

```markdown
# Projet Pokémon TCG - API Spring Boot

## Description

Ce projet est une API REST développée avec Spring Boot permettant de gérer un jeu de cartes inspiré du Pokémon Trading Card Game (TCG). L'application permet aux dresseurs de collectionner, échanger et combattre avec des cartes Pokémon.

Les principales fonctionnalités incluent :
- **Gestion des dresseurs** : Création, suppression et récupération des dresseurs.
- **Gestion des Pokémon** : Ajout, modification, suppression et récupération des Pokémon.
- **Pioche de cartes** : Chaque jour, un dresseur peut tirer un lot de 5 cartes aléatoires.
- **Gestion des paquets de cartes** : Chaque dresseur a un paquet principal (5 cartes pour le combat) et un paquet secondaire.
- **Échange de cartes** : Un dresseur peut échanger une carte entre ses deux paquets à tout moment.
- **Échange entre dresseurs** : Deux dresseurs peuvent échanger des cartes entre eux une fois par jour.
- **Combat entre dresseurs** : Un dresseur peut défier un autre. Le gagnant remporte la meilleure carte du perdant.

## Table des matières

1. [Technologies utilisées](#technologies-utilisées)
2. [Prérequis](#prérequis)
3. [Installation](#installation)
4. [Configuration de la base de données](#configuration-de-la-base-de-données)
5. [EndPoints API](#endpoints-api)
6. [Exemples de requêtes](#exemples-de-requêtes)
7. [Test avec Postman](#test-avec-postman)
8. [Améliorations possibles](#améliorations-possibles)
9. [Auteurs](#auteurs)

## Technologies utilisées

- **Java 17** : Langage de programmation utilisé.
- **Spring Boot** (Web, Data JPA) : Framework pour construire l'API REST.
- **Hibernate** : ORM pour gérer la base de données.
- **PostgreSQL** : Base de données relationnelle (ou autre base compatible JPA).
- **Lombok** : Utilisé pour faciliter la gestion des getters/setters.
- **Postman** : Outil pour tester les endpoints de l'API.

## Prérequis

Avant de commencer, vous devez avoir les éléments suivants installés sur votre machine :
- [Java 17](https://adoptopenjdk.net/) ou version supérieure.
- [Maven](https://maven.apache.org/).
- [PostgreSQL](https://www.postgresql.org/) ou une autre base de données compatible avec JPA.

## Installation

### 1. Cloner le repository

Clonez ce repository sur votre machine locale :

```bash
git clone https://github.com/votre-repository/pokemon-tcg.git
```

### 2. Accédez au dossier du projet

```bash
cd pokemon-tcg
```

### 3. Compiler et exécuter le projet

Compilez le projet et exécutez-le en utilisant Maven :

```bash
mvn clean install
mvn spring-boot:run
```

L'application démarrera sur le port `8080` par défaut.

## Configuration de la base de données

### 1. Créez une base de données PostgreSQL (ou une autre base compatible)

Si vous utilisez PostgreSQL, créez une base de données, par exemple `pokemon_tcg`.

### 2. Configuration dans `application.properties`

Configurez les informations de la base de données dans le fichier `src/main/resources/application.properties` :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pokemon_tcg
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

Cela configure la connexion à la base de données.

## EndPoints API

### 1. **Dresseurs**

- **GET /dresseurs**  
  Récupère la liste de tous les dresseurs.
  
- **POST /dresseurs**  
  Crée un nouveau dresseur.
  - **Body** :
    ```json
    {
      "nom": "Ash",
      "prenom": "Ketchum"
    }
    ```

- **DELETE /dresseurs/{uuid}**  
  Supprime un dresseur par UUID.

### 2. **Pokémon**

- **GET /dresseurs/{uuid}/piocher**  
  Permet à un dresseur de piocher cinq Pokémon aléatoires.

- **PATCH /dresseurs/{uuid}/capturer**  
  Permet à un dresseur de capturer un Pokémon par UUID.  
  - **Body** :
    ```json
    {
      "uuid": "8102e1a6-525b-411c-bf0e-8493d83a5b23"
    }
    ```

### 3. **Échanges**

- **POST /echanges**  
  Permet à deux dresseurs d'échanger des Pokémon.
  - **Body** :
    ```json
    {
      "dresseur1Uuid": "32a65caa-724b-477e-95a4-cca452f5643a",
      "dresseur2Uuid": "5b3b83a7-98d3-49b6-b9ff-8ed7ce5f8899",
      "pokemon1Uuid": "2c6f8912-d863-4607-bb0e-7b920ecc1ab6",
      "pokemon2Uuid": "5453cfda-d2ac-41f8-88be-237882d662e0"
    }
    ```

## Exemples de requêtes

### 1. **Capturer un Pokémon**

- **Méthode** : `PATCH`
- **URL** : `http://localhost:8080/dresseurs/{uuid}/capturer`
- **Body** :
  ```json
  {
    "uuid": "8102e1a6-525b-411c-bf0e-8493d83a5b23"
  }
  ```

### 2. **Échanger des Pokémon**

- **Méthode** : `POST`
- **URL** : `http://localhost:8080/echanges`
- **Body** :
  ```json
  {
    "dresseur1Uuid": "32a65caa-724b-477e-95a4-cca452f5643a",
    "dresseur2Uuid": "5b3b83a7-98d3-49b6-b9ff-8ed7ce5f8899",
    "pokemon1Uuid": "2c6f8912-d863-4607-bb0e-7b920ecc1ab6",
    "pokemon2Uuid": "5453cfda-d2ac-41f8-88be-237882d662e0"
  }
  ```

## Test avec Postman

Vous pouvez tester les endpoints de l'API avec [Postman](https://www.postman.com/).

### 1. **Requête pour capturer un Pokémon :**
- **Méthode** : `PATCH`
- **URL** : `http://localhost:8080/dresseurs/{uuid}/capturer`
- **Body** :
  ```json
  {
    "uuid": "8102e1a6-525b-411c-bf0e-8493d83a5b23"
  }
  ```

### 2. **Requête pour échanger des Pokémon :**
- **Méthode** : `POST`
- **URL** : `http://localhost:8080/echanges`
- **Body** :
  ```json
  {
    "dresseur1Uuid": "32a65caa-724b-477e-95a4-cca452f5643a",
    "dresseur2Uuid": "5b3b83a7-98d3-49b6-b9ff-8ed7ce5f8899",
    "pokemon1Uuid": "2c6f8912-d863-4607-bb0e-7b920ecc1ab6",
    "pokemon2Uuid": "5453cfda-d2ac-41f8-88be-237882d662e0"
  }
  ```

## Améliorations possibles

- Implémentation d'un système de gestion des niveaux et des points d'expérience des Pokémon.
- Ajout de tests unitaires et d'intégration pour une meilleure couverture du code.
- Ajout de la fonctionnalité de gestion des objets et des capacités des Pokémon.
- Gestion des erreurs plus fine (ex. validation de l'UUID, gestion d'erreurs réseau, etc.).

## Auteurs

- **[Votre Nom]** - Développeur principal  
  GitHub : [Votre Profil GitHub](https://github.com/votre-utilisateur)

---

N'hésitez pas à contribuer à ce projet en ouvrant des *issues* ou en envoyant des *pull requests*.
```

### Explication des sections :

1. **Description** : Résumé détaillé du projet, y compris toutes les fonctionnalités principales, fusionnées des deux versions.
2. **Technologies utilisées** : Liste complète des outils et des technologies.
3. **Prérequis** : Ce qu'il faut installer avant de démarrer le projet.
4. **Installation** : Étapes d'installation du projet et de démarrage de l'application.
5. **Configuration de la base de données** : Instructions pour configurer la base de données.
6. **EndPoints API** : Description de chaque endpoint de l'API.
7. **Exemples de requêtes** : Exemples pour tester les principales actions de l'API.
8. **Test avec Postman** : Instructions sur l'utilisation de Postman pour tester l'API.
9. **Améliorations possibles** : Idées pour de futures améliorations du projet.
10. **Auteurs** : Mention de l'auteur principal et de la possibilité de contribuer.