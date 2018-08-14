/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

/**
 *
 * @author paul
 */
public class GeneralTest {
    private static String[] args = new String[0];
    public static String C_BASIC = "\033[0m";
    public static String C_RED = "\033[31m";
    public static String C_GREEN = "\033[32m";


    public static void main(String[] args) {
        AbstractTest.setMultipleTestsMode();
        System.out.println(C_GREEN + "[==========]" + C_BASIC);


        System.out.println(C_GREEN + "[----------]" + C_BASIC);

        Test.main(args);
        Test.test_assert("Test");

        TestMouvementsCartes.main(args);
        TestMouvementsCartes.test_assert("TestMouvementsCartes");

        System.out.println(C_GREEN + "[----------]" + C_BASIC);


        System.out.println(C_GREEN + "[==========]" + C_BASIC + " " + AbstractTest.nb_tests_ran + " tests ran.");

        System.out.println(C_GREEN + "[  PASSED  ]" + C_BASIC + " " + (AbstractTest.nb_tests_ran - AbstractTest.nb_tests_failed) + " tests.");
        if (AbstractTest.nb_tests_failed > 0) {
            System.out.println(C_RED + "[  FAILED  ]" + C_BASIC + " " + AbstractTest.nb_tests_failed + " tests, listed below:");
            System.out.print(AbstractTest.failedTests);
        }
    }
}
