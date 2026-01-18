# Maze JavaFX

Maze est une application JavaFX permettant de jouer dans un labyrinthe généré procéduralement.  
Le joueur se déplace au clavier dans un labyrinthe aléatoire jusqu’à atteindre la case objectif.

Ce projet met l’accent sur :
- la **génération algorithmique de labyrinthes**
- une **architecture claire et testable**
- la séparation stricte entre logique métier, état et rendu
- la persistance simple de l’état du jeu (JSON)

---

## Fonctionnalités

- Génération dynamique de labyrinthes (taille impaire)
- Déplacements clavier (↑ ↓ ← →)
- Détection de victoire
- Reset complet de la partie avec nouveau labyrinthe
- Sauvegarde de la partie (maze + joueur) en JSON
- Chargement automatique au démarrage si une sauvegarde existe
- Suppression de la sauvegarde après chargement
- Tests unitaires sur la logique métier et la génération

---

## Architecture

### GameContext – source unique de vérité

`GameContext` centralise **tout l’état du jeu** :
- labyrinthe (`MazeCells[][]`)
- joueur (`Player`)
- état global (`GameState`)

Aucune autre classe ne possède ou ne duplique cet état.

---

### Rules – logique métier pure

La classe `Rules` :
- valide les déplacements
- applique les règles de collision
- déclenche l’état de victoire

Elle dépend uniquement du `GameContext` et peut être testée sans JavaFX.

---

### Rendering séparé – MazeRenderer

`MazeRenderer` :
- lit uniquement l’état depuis le `GameContext`
- ne contient aucune règle métier
- gère l’affichage incrémental du joueur

---

### Controller JavaFX

Le `Controller` :
- relaie les entrées clavier
- déclenche reset / save & quit
- s’abonne aux changements d’état (`GameState`)

---

## Génération du labyrinthe

La génération repose sur une approche en **deux étapes distinctes** :

### 1. MazeTemplateGenerator
- Algorithme de backtracking
- Exploration complète de la grille
- Ouverture cohérente des murs

### 2. MazeMapper
- Transformation du template abstrait en grille finale
- Gestion des murs, intersections et bordures
- Placement des cases START et GOAL

Cette séparation permet :
- des tests ciblés
- une meilleure lisibilité
- une évolution indépendante des deux étapes

---

## Persistance

La persistance utilise **Jackson** pour la sérialisation JSON :
- encapsulation de l’état via `SavingContainer`
- sauvegarde du labyrinthe et du joueur
- chargement conditionnel au démarrage
- suppression automatique de la sauvegarde après chargement

⚠️ Certains setters et constructeurs vides sont exposés **uniquement par contrainte de désérialisation Jackson**.  
Dans un contexte applicatif plus large, une approche via DTO dédiés serait privilégiée afin de préserver les invariants métier.

---

## Tests

Des tests unitaires sont présents pour :
- les règles de déplacement (`Rules`)
- la génération du template
- le mapping du labyrinthe
- la factory et les invariants structurels

Les tests portent exclusivement sur la logique métier, sans dépendance à JavaFX.

---

## Technologies

- Java 17
- JavaFX
- Maven
- JUnit 5
- Jackson

---

## Lancer le projet

```bash
mvn javafx:run
```

---

## Pistes d’amélioration

- Ajout d’un timer de jeu
- Score ou compteur de déplacements
- Sauvegardes multiples
- Solver automatique du labyrinthe
- Animations JavaFX
- Couche DTO dédiée pour la persistance

