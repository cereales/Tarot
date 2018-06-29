CC=javac
CLASSPATH=-classpath build/
SOURCEPATH=-sourcepath src/
DESTPATH=-d build/
GAMEPATH=src/main/java/game



all: clean tarot test_mouvements_cartes


### Liste des tests

test: Test
	java $(CLASSPATH) game.tests.$< 

test_mouvements_cartes: TestMouvementsCartes
	java $(CLASSPATH) game.tests.$<


### Compilation des tests
%: $(GAMEPATH)/tests/%.java
	$(CC) $(CLASSPATH) $(SOURCEPATH) $(DESTPATH) $<


### Compilation du modele
tarot:
	$(CC) $(CLASSPATH) $(SOURCEPATH) $(DESTPATH) $(GAMEPATH)/tarot/*.java



clean:
	rm -rf build/game/tests/*.class
	rm -rf build/game/tarot/*.class

