CC=javac
CLASSPATH=-classpath build/
SOURCEPATH=-sourcepath src/
DESTPATH=-d build/
GAMEPATH=src/main/java/game



all: clean utils tests
utils: tarot
tests: utils AbstractTest tests_list
tests_list: test_Test test_TestMouvementsCartes test_GeneralTest

# needed, but why...
useless_list: Test TestMouvementsCartes GeneralTest



### Execution des tests
test_%: %
	java $(CLASSPATH) game.tests.$<

### Compilation du modele
tarot:
	$(CC) $(CLASSPATH) $(SOURCEPATH) $(DESTPATH) $(GAMEPATH)/tarot/*.java

### Compilation des tests
%: $(GAMEPATH)/tests/%.java
	$(CC) $(CLASSPATH) $(SOURCEPATH) $(DESTPATH) $<



clean:
	rm -rf build/game/tests/*.class
	rm -rf build/game/tarot/*.class

