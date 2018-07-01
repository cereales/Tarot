CC=javac
CLASSPATH=-classpath build/
SOURCEPATH=-sourcepath src/
DESTPATH=-d build/
GAMEPATH=src/main/java/game

# Debug
all: compile _tests

# Ajouter les tests ici
_tests_list: _test_Test _test_TestMouvementsCartes



### Internal

compile: clean _utils
test: _test_GeneralTest
_utils: _tarot
_tests: _utils AbstractTest _tests_list

# needed, but why...
_useless_list: Test TestMouvementsCartes GeneralTest



### Execution des tests
_test_%: %
	java $(CLASSPATH) game.tests.$<

### Compilation du modele
_tarot:
	$(CC) $(CLASSPATH) $(SOURCEPATH) $(DESTPATH) $(GAMEPATH)/tarot/*.java

### Compilation des tests
%: $(GAMEPATH)/tests/%.java
	$(CC) $(CLASSPATH) $(SOURCEPATH) $(DESTPATH) $<



clean:
	rm -rf build/game/tests/*.class
	rm -rf build/game/tarot/*.class

