# Tarot
Ce projet utilise l'API JDA (https://github.com/DV8FromTheWorld/JDA).
L'objectif de ce projet est de permettre de jouer au tarot à l'aide de l'interface Discord (https://discordapp.com).


## Débuter

### Mise en place de l'environnement
Après avoir cloné le git, initialiser l'environnement du projet:

```
make init
```

Insérer le token du bot dans le fichier `src/main/java/fr/PrivateTokenised.java`.


### Création du fichier .jar

L'utilisation d'IntelliJ est conseillée pour compiler les sources.

Ouvrir un nouveau projet. Cocher la case `Use auto-import`.

Dans `File > Project Structure (Crtl + Alt + Maj + S) > Artifacts`, créer un nouvel Artifact `JAR > From modules with dependencies...`.
Ajouter la Main Class et modifier le path du META-INF de `src/main/java` à `src/main/ressource`.
Si le dossier `ressource` existe déjà, le supprimer entièrement.

Ensuite, `build > build Artefacts`.

Pour lancer le programme:
```
java -jar JDA.jar
```


## Contribuer

### Configuration du git

#### master et experimental
Le git du projet contient une branche `master` destinée à recevoir des états utilisables par les utilisateurs.

C'est sur la branche `experimental` que les sous-branches doivent être pushed lorsque la mise à jour est terminée.

#### mise en forme d'un commit
Chaque commit doit suivre le modèle de syntaxe suivant:

```* [<nom_de_la_branche>] <Titre_de_la_mise_à_jour>```

Exemple : ```* [discord] Ajout de l'API JDA```

#### noms des sous-branches
Des *issues* sont ouvertes sur *GitHub*. Elles comportent en tag le nom de la branche qui accueille la solution.


### Les tests

#### Tester
Le test `GeneralTest` doit être lancé avant de push. Il permet de lancer tous les tests sans affichage, et de vérifier leur bon déroulement.

#### Créer un test
Les tests doivent tous hériter du type `AbstractTest`. Cela donner l'accès à des méthodes utiles.

- *println* : Le test ne doit pas faire d'affichage autrement qu'à l'aide de cette méthode.
- *ASSERT_EQ* : Le test doit utiliser au maximum cette fonction pour valider les états des variables. Vérifie que les objets sont égaux.
- *ASSERT_NE* : Le test doit utiliser au maximum cette fonction pour valider les états des variables. Vérifie que les objets sont différents.

Les tests créés doivent être ajoutés au test `GeneralTest`:
- Ajouter un appel à `main`
- Puis appeler la méthode *test_assert* avec en paramètre le nom du test.



