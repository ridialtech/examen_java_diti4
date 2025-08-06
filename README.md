# Gestion des Locations d'Immeubles

Cette application web Spring Boot permet de gérer la location d'immeubles. Elle offre aux propriétaires, locataires et administrateurs un ensemble de fonctionnalités pour suivre les immeubles, unités de location, contrats et paiements.

## Fonctionnalités

- **Gestion des immeubles** : création, modification, suppression et consultation des immeubles avec leurs détails (adresse, description, nombre d'unités, équipements).
- **Gestion des unités de location** : ajout, modification, suppression des appartements (numéro, surface, pièces, loyer, statut).
- **Gestion des locataires** : inscription, authentification, consultation des offres filtrées, envoi et suivi des demandes de location.
- **Contrats et paiements** : création des contrats de location, suivi des paiements mensuels, génération de reçus PDF et relances automatiques.
- **Administration** : gestion des utilisateurs, tableau de bord, rapports statistiques sur les contrats et paiements.

## Technologies

- **Backend** : Spring Boot, Spring MVC, Spring Data JPA, Spring Security, Lombok
- **Frontend** : Thymeleaf, Bootstrap
- **Base de données** : MySQL ou PostgreSQL
- **Outils** : Maven ou Gradle, JUnit, Flyway/Liquibase (optionnel)

## Architecture

### Modèle de données (JPA)

- `Immeuble` → plusieurs `UniteLocation`
- `Locataire` → plusieurs `Contrat`
- `Contrat` → une `UniteLocation` + un `Locataire`
- `Paiement` → un `Contrat`

### Architecture logicielle

- **Controller** : gère les requêtes HTTP
- **Service** : logique métier
- **Repository** : accès aux données via Spring Data JPA
- **View** : pages Thymeleaf

## Exécution

1. Installer JDK 21 et Gradle.
2. Cloner le dépôt :
   ```bash
   git clone <url-du-depot>
   cd examen_java_diti4
   ```
3. Lancer les tests :
   ```bash
   ./gradlew test
   ```
4. Démarrer l'application :
   ```bash
   ./gradlew bootRun
   ```

## Contributeurs

- M LO Makhmadane

