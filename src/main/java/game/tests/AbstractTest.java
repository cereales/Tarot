/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows tests to use ASSERT_xx functions.
 *
 * @author paul
 */
public abstract class AbstractTest {
    private static boolean isSingleTest = true;
    private static int asserted = 0;
    private static int failed = 0;
    protected static int nb_tests_ran = 0;
    protected static int nb_tests_failed = 0;
    protected static String failedTests = "";
    private static List<AssertionError> print = new ArrayList();

    public AbstractTest() {
    }

    public static void setMultipleTestsMode() {
        isSingleTest = false;
    }

    /**
     * Allows to test with assert functions.
     */
    public static void test_assert(String testName) {
        System.out.println(GeneralTest.C_GREEN + "[  RUN     ]" + GeneralTest.C_BASIC + " " + testName);
        for (AssertionError e : print)
            e.printStackTrace();
        if (failed > 0) {
            String failTrace = GeneralTest.C_RED +"[  FAILED  ]" + GeneralTest.C_BASIC + " " + testName;
            failedTests += failTrace + "\n";
            System.out.println(failTrace);
            ++nb_tests_failed;
        }
        else
            System.out.println(GeneralTest.C_GREEN + "[      OK  ]" + GeneralTest.C_BASIC + " " + testName);

        failed = 0;
        asserted = 0;
        print = new ArrayList();
        ++nb_tests_ran;
    }


    /* outils pour tests */

    public static void println(Object str) {
        if (isSingleTest)
            System.out.println(str);
    }

    
    public static void ASSERT_EQ(Object num1, Object num2) {
        boolean test_failed = false;
        try {
            assert (num1.equals(num2));
        } catch (AssertionError e) {
            if (isSingleTest)
                throw e;
            else {
                print.add(e);
                test_failed = true;
            }
        }
        ASSERT(test_failed);
    }

    public static void ASSERT_NE(Object num1, Object num2) {
        boolean test_failed = false;
        try {
            assert (!num1.equals(num2));
        } catch (AssertionError e) {
            if (isSingleTest)
                throw e;
            else {
                print.add(e);
                test_failed = true;
            }
        }
        ASSERT(test_failed);
    }

    private static void ASSERT(boolean fail) {
        ++asserted;
        if (fail) {
            ++failed;
        }
    }
}
