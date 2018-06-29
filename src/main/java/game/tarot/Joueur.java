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
public class Joueur {
    protected List<Carte> main;
    
    
    /**
     * Constructeur d'un compte déjà existant.
     * @param id du joueur enregistré
     * @param mdp
     * @throws IllegalArgumentException si le compte n'existe pas.
     */
    public Joueur(String id, String mdp) throws IllegalArgumentException {
        main = new ArrayList();
        // Debug
        
    }
    
    /**
     * Constructeur d'un compte non existant.
     * @param id
     * @param mdp
     * @param email 
     * @throws IllegalArgumentException si le pseudo est déjà pris.
     */
    public Joueur(String id, String mdp, String email)
            throws IllegalArgumentException {
    }
    
    /**
     * Ajoute les score au profil et reinitialise les scores du joueur.
     * @param score 
     */
    public void deconnecter(Score score) {
    }
    
    
    /**
     * Permet de couper le jeu de carte.
     * La coupe ne doit pas etre plus petite qu'un plis (4%).
     * @param pourcentage entier compris entre 7 et 93.
     * @return false si la coupe est interdite.
     */
    public boolean couper(Table table, int pourcentage) {
        return false;
    }
    
    /**
     * Demander la redistribution.
     * @return false si refus.
     */
    public boolean redistribuer() {
        return false;
    }
    
    /**
     * Fait une annonce.
     * 0 parole
     * 1 petite
     * 2 garde
     * 3 garde sans
     * 4 garde contre
     * @param table
     * @param mise annonce
     * @return false si l'annonce est interdite.
     */
    public boolean parler(Table table, int mise) {
        return false;
    }
    
    /**
     * Appel un roi.
     * @param table
     * @param couleur
     * @return false si l'appel est interdit.
     */
    public boolean appeler(Table table, Couleur couleur) {
        return false;
    }
    
    /**
     * Appel une carte autre qu'un roi.
     * @param table
     * @param carte
     * @return false si l'appel est interdit.
     */
    public boolean appeler(Table table, Carte carte) {
        return false;
    }
    
    /**
     * Fait une annonce.
     * Si période du chien, permet de valider le chien.
     * @param table
     * @param annonce
     * @return False si l'annonce est interdite.
     */
    public boolean annoncer(Table table, Annonce annonce) {
        return false;
    }
    
    /**
     * Joue une carte.
     * Si période de l'écart, permet de placer ou recuperer une carte.
     * @param table
     * @param carte
     * @return False si le coup est interdit.
     */
    public boolean jouer(Table table, Carte carte) {
        return false;
    }
}
