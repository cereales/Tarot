/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

import game.connexion.Computer;
import game.connexion.Profil;
import game.tarot.Carte;
import game.tarot.Couleur;
import game.tarot.JeuCartes;
import game.tarot.Joueur;
import game.connexion.SingletonJoueurs;
import game.tarot.Table;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paul
 */
public class TestTable extends AbstractTest {
    public static void main(String[] args) {
        println("\n##  Start  ##\n");

        SingletonJoueurs singleton = SingletonJoueurs.getOccurence();
        Table table = new Table();
        println(table);
        
        Profil Pbob = new Profil("bob", "mdp1");
        Joueur bob = Pbob.getJoueur();
        ASSERT_EQ(true, table.rejoindre(Pbob, bob));
        ASSERT_EQ(false, table.rejoindre(Pbob, bob));

        Computer Ppaul = new Computer();
        Joueur paul = Ppaul.getJoueur();
        ASSERT_EQ(true, table.rejoindre(Ppaul, paul));

        Computer Ppierre = new Computer();
        Joueur pierre = Ppierre.getJoueur();
        ASSERT_EQ(true, table.rejoindre(Ppierre, pierre));

        Computer Pjacques = new Computer();
        Joueur jacques = Pjacques.getJoueur();
        ASSERT_EQ(true, table.rejoindre(Pjacques, jacques));

        
        println("\n##   End   ##\n");
    }
}
