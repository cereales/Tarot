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
        Joueur[] joueurs = {bob, paul, pierre, jacques};
        
        ASSERT_EQ(true, jeu.couper(10));
        println("Coupe :\n" + jeu);
        
        ASSERT_EQ(true, jeu.distribuer(0));
        println("Distribution :\n" + jeu);
        
        jeu.abandonner();
        println("Abandon :\n" + jeu);
        
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
        int nbAtouts= 0;
        for (Carte _carte : bob.getMain())
            if (_carte.getCouleur().equals(Couleur.ATOUT))
                ++nbAtouts;
        if (nbAtouts >= 14) {
            for (int i = 0; i < 6; ++i) {
                do {
                    carte = bob.getMain().get((int) (random() * bob.getMain().size()));
                } while (carte.getCouleur().equals(Couleur.ATOUT) || carte.getValeur() == 14);
                ASSERT_EQ(true, jeu.faireEcart(carte, 0));
            }
            println("Faire un chien :\n" + jeu);
            ASSERT_EQ(true, jeu.validerEcart());
            jeu.donnerChien(0);
        }
        
        for (int i = 0; i < 30; ++i) {
            ASSERT_EQ(true, new Carte(21, Couleur.ATOUT).winsAgainst(new Carte((int) (20 * random()), Couleur.values()[(int) (5 * random())])));
            ASSERT_EQ(false, new Carte(0, Couleur.ATOUT).winsAgainst(new Carte((int) (20 * random() + 1), Couleur.values()[(int) (5 * random())])));
        }
        ASSERT_EQ(true, new Carte(12, Couleur.PIQUE).winsAgainst(new Carte(5, Couleur.PIQUE)));
        ASSERT_EQ(true, new Carte(5, Couleur.PIQUE).winsAgainst(new Carte(8, Couleur.TREFLE)));
        ASSERT_EQ(true, new Carte(5, Couleur.ATOUT).winsAgainst(new Carte(8, Couleur.TREFLE)));
        ASSERT_EQ(true, new Carte(5, Couleur.ATOUT).winsAgainst(new Carte(4, Couleur.ATOUT)));
        
        int id = 0;
        Carte carte0 = JeuCartes.bouts.get(0);
        for (int j = 4; j > 0; --j) {
            List<Carte> tmp = joueurs[id].getAuthorized(carte0, 0);
            if (tmp.contains(JeuCartes.bouts.get(0)))
                carte = JeuCartes.bouts.get(0);
            else
                carte = tmp.get((int) (random() * tmp.size()));
            if (carte0.equals(JeuCartes.bouts.get(0)) && id <= 1)
                carte0 = carte;
            println(carte);
            ASSERT_EQ(true, jeu.jouer(id, carte));
            id = jeu.nextCircleIndex(id, 0, 4);
        }
        println("Joue une carte chacun :\n" + jeu);
        id = jeu.nouveauLanceur(0);
        jeu.rendreCarteFromExcuse(new boolean[]{true, true, false, false}, true);
        jeu.ammasserCartes(false);
        println("Resoud le pli : gagnant J" + (id + 1) + "\n" + jeu);
        
        for (int j = 0; j < 4; ++j) {
            for (int i = 0; i < 4; ++i)
                for (int k = 0; k < 4; ++k) {
                    jeu.jouer(j, joueurs[j].getMain().get(0));
                    jeu.ammasserCartes(false);
                }
        }
        for (int j = 0; j < 4; ++j)
            jeu.jouer(j, joueurs[j].getMain().get(0));
        jeu.ammasserCartes(false);
        ASSERT_NE(-1, jeu.compterPoints());
        ASSERT_EQ(true, jeu.ramasser());
        println("Vider  :\n" + jeu);
        ASSERT_EQ(true, jeu.distribuer(0));
        println("Redistribuer :\n" + jeu);
        
        println("##   End   ##\n");
    }
}
