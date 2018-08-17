/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

import game.connexion.Profil;
import game.connexion.SingletonJoueurs;
import game.tarot.Table;
import static game.tests.AbstractTest.println;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author paul
 */
public class TestInterface extends AbstractTest {
    public static void main(String[] args) {
        println("\n##  Start  ##\n");
        Profil user = null;
        
        //mainInterface(user);
        avancedInterface(user);
        //finalInterface(user);
        
        println("\n##   End   ##\n");
    }


    private static void mainInterface(Profil user) {
        String tmp;
        
        Scanner reader = new Scanner(System.in);
        do {
            System.out.print("Pseudo : ");
            tmp = reader.next();
            System.out.print("Password : ");
            try {
                user = new Profil(tmp, reader.next());
            } catch (IllegalArgumentException e) {}
        } while (user == null);
        String question = user.execute(0);
        do {
            System.out.println(question);
            System.out.print(">>> ");
            question = user.execute(reader.nextInt());
        } while (user.isConnected());
        reader.close();
    }

    private static void avancedInterface(Profil user) {
        Scanner reader = new Scanner(System.in);
        user = new Profil("bob", "mdp1");
        String question = user.execute(0);
        question = user.execute(2);
        question = user.execute(3);
        question = user.execute(2);
        question = user.execute(1);
        question = user.execute(2);
        question = user.execute(1);
        do {
            System.out.println(question);
            System.out.print(">>> ");
            question = user.execute(reader.nextInt());
        } while (user.isConnected());
        reader.close();
    }

    public static void finalInterface(Profil user) {
        user = new Profil("bob", "mdp1");
        String question = user.execute(0);
        question = user.execute(2);
        question = user.execute(3);
        question = user.execute(2);
        question = user.execute(1);
        question = user.execute(2);
        question = user.execute(1);
        //TODO

        // exit
        question = user.execute(1);
        question = user.execute(1);
        question = user.execute(1);
        SingletonJoueurs.getOccurence().disconnect("bob");
    }
}
