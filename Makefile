CC=javac
CLASSPATH=-classpath target/classes/
SOURCEPATH=-sourcepath src/
DESTPATH=-d target/classes/
GAMEPATH=src/main/java/game

# Debug
all: init


### Internal
init:
	@echo "LOGIN;MDP\nbob;mdp1" > src/main/java/game/connexion/database.txt
	cp sources/.PrivateInformations src/main/java/fr/connexion/PrivateInformations.java
	./gradlew build
	mkdir target/
	mkdir target/classes/
