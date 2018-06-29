/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tarot;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author paul
 */
public class JeuCartes {
    private int nbJoueurs;
    
    private List<Carte> paquet;
    private List<Joueur> joueurs; // Pour stocker les mains et les modifier.
    private List<Carte> table; // Pour le chien et le jeu.
    private List<Carte> plisAttaque;
    private List<Carte> plisDefense;
    
    
    public JeuCartes() {
        paquet = new ArrayList<Carte>();
        joueurs = new ArrayList<Joueur>();
        table = new ArrayList<Carte>();
        plisAttaque = new ArrayList<Carte>();
        plisDefense = new ArrayList<Carte>();

        // Debug
        for (int i = 0; i < 89; ++i) paquet.add(new Carte(i, new Couleur(0)));
        nbJoueurs = 2;
        joueurs.add(new Joueur("bob", "bob"));
        joueurs.add(new Joueur("paul", "paul"));
    }
    
    
    /**
     * Permet de couper le jeu de carte.
     * De l'aléatoire est ajouté.
     * @param pourcentage entier compris entre 7 et 93.
     * @return false si la coupe est interdite.
     */
    public boolean couper(int pourcentage) {
        return false;
    }
    
    /**
     * Distribue les cartes.
     * @param nbJoueurs
     * @return false si le nombre de joueur est interdit.
     */
    public boolean distribuer(int nbJoueurs) {
        return false;
    }
    
    /**
     * Rassemble les cartes.
     * @return false si l'action est interdite.
     */
    public boolean ramasser() {
        return false;
    }
    
    /**
     * Abandonne la partie en cours et ramasse les cartes.
     */
    public void abandonner() {
    }
    
    
    /**
     * Placer les cartes dans la main du joueur.
     * @param joueur 
     */
    public void donnerChien(int joueur) {
    }
    
    /**
     * Mettre les cartes du chien dans les plis de l'equipe.
     * @param attaque : si true, les point vont à l'attaque. Sinon à la défense.
     */
    public void ammasserChien(boolean attaque) {
    }
    
    /**
     * L'attaque fait son écart.
     * Si la carte est déjà dans le chien,
     * elle est remise dans la main du joueur.
     * Le nombre de cartes n'est pas controlé.
     * @param carte doit etre à la bonne personne
     * @return false si la carte n'appartient pas au joueur.
     */
    public boolean faireEcart(Carte carte, int joueur) {
        return false;
    }
    
    /**
     * Valide l'écart.
     * @return false si le nombre de cartes n'est pas le bon.
     */
    public boolean validerEcart() {
        return false;
    }
    
    
    /**
     * Place une carte sur la table. Le premier appel initialise la couleur
     * demandée.
     * Pas de controle sur le respect des tours.
     * @param carte doit appartenir au joueur dont c'est le tour.
     * @return false si l'action est interdite.
     */
    public boolean jouer(Carte carte) {
        return false;
    }
    
    /**
     * Ressoud le plis précédant et renvoie le numéro du prochain joueur à
     * jouer.
     * Vérifie que le nombre de cartes tombées correspond au nombre de joueurs.
     * Résoudre le plis signifie gérer le cas de l'excuse.
     * @return le numéro du prochain joueur, cad le gagnant du plis.
     *         -1 si erreur.
     */
    public int nouveauLanceur() {
        return -1;
    }
    
    
    /**
     * Compte les points des plis de l'attaque.
     * @return le resultat.
     */
    public int compterPoints() {
        return -1;
    }
    

    /**
     * Only for debug.
     */
    @Override
    public String toString() {
        String res = "";
        String sep = ".----------.-------------------------------------------------------------.";

        res += sep + '\n';
        res += "|  PAQUET  |" + addCartesOf(paquet, sep) + '\n';
        for (int n = 0; n < nbJoueurs; ++n) {
            res += "|    J" + (n + 1) + "    |" + addCartesOf(joueurs.get(n).main, sep) + '\n';
        }
        res += "|  TABLE   |" + addCartesOf(table, sep) + '\n';
        res += "| PLIS ATT |" + addCartesOf(plisAttaque, sep) + '\n';
        res += "| PLIS DEF |" + addCartesOf(plisDefense, sep);

        return res;
    }

    /**
     * Returns what is in the paquet.
     */
    private String addCartesOf(List<Carte> paquet, String sep) {
        String res = " ";
        int i = 0;
        while (i < paquet.size()) {
            if (i % 20 == 0 && i != 0) res += "|\n|          | ";
            res += paquet.get(i++) + " ";
        }
        for (int tmp = 0; tmp < 19 - (i-1) % 20; ++tmp) res += "   ";
        res += "|\n" + sep;
        return res;
    }
}
