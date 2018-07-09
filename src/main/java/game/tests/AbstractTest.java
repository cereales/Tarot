/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Allows tests to use ASSERT_xx functions.
 *
 * @author paul
 */
public abstract class AbstractTest {
    private static boolean isSingleTest = true;
    
    public AbstractTest() {
    }
    
    /**
     * Allows to test with assert functions.
     */
    public void test_assert() throws FileNotFoundException {
        isSingleTest = false;
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static void println(Object str) {
        if (isSingleTest)
            System.out.println(str);
    }

    
    public static void ASSERT_EQ(Object num1, Object num2) {
        //TODO
        assert(num1.equals(num2));
    }
}
