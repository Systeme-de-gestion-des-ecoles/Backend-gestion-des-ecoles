# Backend - Système de gestion des écoles 🎓

Backend du projet **Système de gestion des écoles**, réalisé dans le cadre de l'**UV Projet de Fin de Cycle de Licence** à l'**Université de Dschang**.

Ce dépôt regroupe l'ensemble des microservices développés par l'**équipe Backend**, dont j'étais le chef d'équipe. Le projet a obtenu la note de **16/20**.

## 🏗️ Organisation du projet

Le projet global a été structuré en organisation GitHub (**Systeme-de-gestion-des-ecoles**), avec :
- Une **équipe Frontend** et une **équipe Backend**, chacune avec son propre dépôt
- Une gestion des **permissions** par équipe et par membre
- Une répartition des membres selon leurs compétences (Frontend / Backend)

## 🛠️ Stack technique

- **Langage** : Java 21
- **Framework** : Spring Boot 3.5.0
- **Architecture** : Microservices
- **Outils** : Maven, Lombok, Spring Boot DevTools

## 🧩 Microservices

Le backend est découpé en 9 microservices indépendants, chacun responsable d'un domaine métier précis :

| Microservice | Rôle |
|---|---|
| `service-authentification-compte` | Authentification et gestion des comptes utilisateurs |
| `service-admin-scolaire` | Administration scolaire |
| `service-matiere-classe-note` | Gestion des matières, classes et notes |
| `service-comportement-abscence` | Suivi du comportement et des absences |
| `service-calendrier-evenement` | Calendrier et gestion des événements |
| `service-parent-eleve` | Gestion de la relation parent-élève |
| `service-transport-logistique` | Transport et logistique |
| `service-cuisine` | Gestion de la cantine/cuisine |
| `service-notification` | Envoi de notifications |

Chaque microservice est un projet Spring Boot autonome, avec sa propre configuration (`application.properties`), son propre `pom.xml` et son propre cycle de build/déploiement.

## 🚀 Installation et lancement

Chaque microservice se lance indépendamment. Exemple pour un service donné :

```bash
cd service-<nom-du-service>
./mvnw spring-boot:run
```

> Prérequis : Java 21 et Maven (ou utiliser le wrapper `mvnw` inclus dans chaque service).

## 👥 Équipe Backend

Projet réalisé en groupe, sous ma supervision en tant que chef d'équipe Backend, dans le cadre de l'UV Projet de Fin de Cycle de Licence à l'Université de Dschang.

## 📌 Statut du projet

Projet académique finalisé — note obtenue : **16/20**.

## 📄 Licence

Projet académique — Université de Dschang.
