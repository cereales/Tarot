/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

import game.connexion.Profil;
import game.connexion.SingletonJoueurs;

/**
 *
 * @author paul
 */
public class TestConnexion extends AbstractTest {
    public static void main(String[] args) {
        println("\n##  Start  ##\n");
        
        SingletonJoueurs singleton = SingletonJoueurs.getOccurence();
        try {
            Profil tmp1 = new Profil("bob", "mdp");
            ASSERT_EQ(1, 2); // ne doit pas etre execute
        } catch (IllegalArgumentException e) {}
        Profil Pbob = new Profil("bob", "mdp1");
        try {
            Profil tmp1 = new Profil("bob", "mdp1");
            ASSERT_EQ(1, 2); // ne doit pas etre execute
        } catch (IllegalArgumentException e) {}
        
        singleton.createUser("pierre", "mdp");
        singleton.disconnect("bob");
        singleton.disconnect("pierre");

        println("\n##   End   ##\n");
    }
}
