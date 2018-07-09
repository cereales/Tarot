/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

import game.tarot.JeuCartes;
import game.tarot.Joueur;

/**
 *
 * @author paul
 */
public class TestMouvementsCartes extends AbstractTest {
    public static void main(String[] args) {
        println("\n##  Start  ##");

        JeuCartes jeu = new JeuCartes();
        println("Creation du jeu :\n" + jeu);
        ASSERT_EQ(0, jeu.addJoueur(new Joueur("bob", "bob")));
        ASSERT_EQ(1, jeu.addJoueur(new Joueur("paul", "paul")));
        ASSERT_EQ(2, jeu.addJoueur(new Joueur("pierre", "pierre")));
        ASSERT_EQ(3, jeu.addJoueur(new Joueur("jacques", "jacques")));
        
        ASSERT_EQ(true, jeu.couper(10));
        println("Coupe :\n" + jeu);
        
        ASSERT_EQ(true, jeu.distribuer(0));
        println("Distribution :\n" + jeu);
        
        println("##   End   ##\n");
    }
}
