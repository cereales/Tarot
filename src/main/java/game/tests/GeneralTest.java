/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

import static fr.connexion.C_COULEUR.*;

/**
 *
 * @author paul
 */
public class GeneralTest {
    private static String[] args = new String[0];


    public static void main(String[] args) {
        AbstractTest.setMultipleTestsMode();
        System.out.println(C_GREEN + "[==========]" + C_BASIC);


        System.out.println(C_GREEN + "[----------]" + C_BASIC);

        try {
            Test.main(args);
            Test.test_assert("Test");

            TestMouvementsCartes.main(args);
            TestMouvementsCartes.test_assert("TestMouvementsCartes");

            TestTable.main(args);
            TestTable.test_assert("TestTable");

            TestConnexion.main(args);
            TestConnexion.test_assert("TestConnexion");

            TestInterface.main(args);
            TestInterface.test_assert("TestInterface");
        } catch (Exception e) {
            AbstractTest.nb_tests_failed++;
        }

        System.out.println(C_GREEN + "[----------]" + C_BASIC);


        System.out.println(C_GREEN + "[==========]" + C_BASIC + " " + AbstractTest.nb_tests_ran + " tests ran.");

        System.out.println(C_GREEN + "[  PASSED  ]" + C_BASIC + " " + (AbstractTest.nb_tests_ran - AbstractTest.nb_tests_failed) + " tests.");
        if (AbstractTest.nb_tests_failed > 0) {
            System.out.println(C_RED + "[  FAILED  ]" + C_BASIC + " " + AbstractTest.nb_tests_failed + " tests, listed below:");
            System.out.print(AbstractTest.failedTests);
        }
    }
}
