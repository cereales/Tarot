CC=javac
CLASSPATH=-classpath target/classes/
SOURCEPATH=-sourcepath src/
DESTPATH=-d target/classes/
GAMEPATH=src/main/java/game
JAR=jarDirectory

# Debug
all: init


### Internal
init:
	@echo "LOGIN;MDP\nbob;mdp1" > sources/database.txt
	cp sources/.PrivateInformations src/main/java/fr/connexion/PrivateInformations.java
	./gradlew build
	mkdir -p target/
	mkdir -p target/classes/

generate:
	rm -rf $(JAR)
	mkdir $(JAR)
	cp classes/artifacts/JDA_jar/JDA.jar $(JAR)
	cp sources/database.txt $(JAR)

