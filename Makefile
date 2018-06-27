CC=javac
CLASSPATH=-classpath build/
SOURCEPATH=-sourcepath src/
DESTPATH=-d build/
GAMEPATH=src/main/java/game

all: test


test: build/game/tests/Test.class tarot
	java $(CLASSPATH) game.tests.Test 

build/game/tests/Test.class: src/main/java/game/tests/Test.java
	$(CC) $(CLASSPATH) $(SOURCEPATH) $(DESTPATH) $(GAMEPATH)/tests/Test.java 


tarot:
	$(CC) $(CLASSPATH) $(SOURCEPATH) $(DESTPATH) $(GAMEPATH)/tarot/*.java



clean:
	rm -rf build/game/tests/*.class
	rm -rf build/game/tarot/*.class

