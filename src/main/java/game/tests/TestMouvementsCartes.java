/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

import game.tarot.JeuCartes;

/**
 *
 * @author paul
 */
public class TestMouvementsCartes extends AbstractTest {
    public static void main(String[] args) {
        println("\n##  Start  ##");

        JeuCartes jeu = new JeuCartes();
        println("Creation du jeu :\n" + jeu);
        
        jeu.couper(50);
        println(jeu);
        
        println("##   End   ##\n");
    }
}
