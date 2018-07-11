/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tests;

import game.tarot.Carte;
import game.tarot.Couleur;
import game.tarot.JeuCartes;
import game.tarot.Joueur;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paul
 */
public class TestMouvementsCartes extends AbstractTest {
    public static void main(String[] args) {
        println("\n##  Start  ##");

        JeuCartes jeu = new JeuCartes();
        println("Creation du jeu :\n" + jeu);
        Joueur bob = new Joueur("bob", "bob");
        Joueur paul = new Joueur("paul", "paul");
        Joueur pierre = new Joueur("pierre", "pierre");
        Joueur jacques = new Joueur("jacques", "jacques");
        ASSERT_EQ(0, jeu.addJoueur(bob));
        ASSERT_EQ(1, jeu.addJoueur(paul));
        ASSERT_EQ(2, jeu.addJoueur(pierre));
        ASSERT_EQ(3, jeu.addJoueur(jacques));
        
        ASSERT_EQ(true, jeu.couper(10));
        println("Coupe :\n" + jeu);
        
        ASSERT_EQ(true, jeu.distribuer(0));
        println("Distribution :\n" + jeu);
        
        jeu.ammasserCartes(true);
        println("Plis a l'attaque :\n" + jeu);
        
        bob.getMain().remove(0);
        bob.getMain().set(0, new Carte(0, Couleur.ATOUT));
        ASSERT_EQ(18, bob.getMain().size());
        println("Corruption echoue :\n" + jeu);
        
        ASSERT_EQ(false, jeu.jouer(1, bob.getMain().get(0)));
        Carte carte = bob.getMain().get(0);
        ASSERT_EQ(true, jeu.jouer(0, carte));
        ASSERT_EQ(true, jeu.jouer(0, bob.getAuthorized(carte, carte.getValeur()).get(0)));
        ASSERT_EQ(16, bob.getMain().size());
        println("Jouer carte :\n" + jeu);
        
        jeu.donnerChien(0);
        ASSERT_EQ(18, bob.getMain().size());
        bob.trier();
        paul.trier();
        pierre.trier();
        jacques.trier();
        println("Rend les cartes :\n" + jeu);
        
        ASSERT_EQ(false, jeu.distribuer(0));
        // verifier cas impossible de mettre autre chose dans le chien
        for (int i = 0; i < 6; ++i) {
            do {
                carte = bob.getMain().get((int) (random() * bob.getMain().size()));
            } while (carte.getCouleur().equals(Couleur.ATOUT) || carte.getValeur() == 14);
            ASSERT_EQ(true, jeu.faireEcart(carte, 0));
        }
        println("Faire un chien :\n" + jeu);
        ASSERT_EQ(true, jeu.validerEcart());
        
        println("##   End   ##\n");
    }
}
