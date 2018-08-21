CC=javac
CLASSPATH=-classpath target/classes/
SOURCEPATH=-sourcepath src/
DESTPATH=-d target/classes/
GAMEPATH=src/main/java/game

# Debug
all: init


### Internal

init:
	./gradlew build
	mkdir target/
	mkdir target/classes/
