CC=javac
CLASSPATH=-classpath target/classes/
SOURCEPATH=-sourcepath src/
DESTPATH=-d target/classes/
GAMEPATH=src/main/java/game

# Debug
all: compile _tests

# Ajouter les tests ici
# SYNTAXE : _test_XX (XX = name of class)
_tests_list: _test_Test _test_TestMouvementsCartes



### Internal

init:
	mkdir target/
	mkdir target/classes/
compile: clean _utils
	@echo ""
	@echo "******  SUCCES  ******"
	@echo ""
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
	rm -rf target/classes//game/tests/*.class
	rm -rf target/classes//game/tarot/*.class

