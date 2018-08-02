/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

import game.tarot.Couleur;
import game.tarot.Etat;

/**
 *
 * @author paul
 */
public class Test extends AbstractTest {
    public static void main(String[] args) {
        System.out.println("\n##  Start  ##");

        Couleur c = Couleur.ATOUT;
        println("test import : " + c);

        ASSERT_EQ(0, 0);
        
        Etat etat = Etat.INSCRIPTION;
        println("test etat : " + etat);
        etat = Etat.ABANDON;
        println("test etat : " + etat);
        
        println("ecart enum : " + Etat.INSCRIPTION.compareTo(Etat.INTERRUPTION));
        println("ecart enum : " + Etat.INTERRUPTION.compareTo(Etat.INTERRUPTION));
        println("ecart enum : " + Etat.INTERRUPTION.compareTo(Etat.INSCRIPTION));
        
        System.out.println("##   End   ##\n");
    }
}
