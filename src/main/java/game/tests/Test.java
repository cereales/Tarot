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
public class Test {
    public static void main(String[] args) {
        System.out.println("\n##  Start  ##");

        Couleur c = Couleur.ATOUT;
        System.out.println("test import : " + c);
        
        Etat etat = Etat.INSCRIPTION;
        System.out.println("test etat : " + etat);
        etat = Etat.ABANDON;
        System.out.println("test etat : " + etat);
        
        System.out.println("##   End   ##\n");
    }
}
