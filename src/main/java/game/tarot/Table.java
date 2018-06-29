/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tarot;

import java.util.Set;


/**
 *
 * @author paul
 */
public class Table {
    private Joueur[] joueurs;
    private Score[] scores; // scores depuis la connexion
    private JeuCartes jeu;
    
    private int appelant;
    private int appelé;
    private int mise;
    private Set<Annonce> annoncesAttaque;
    private Set<Annonce> annoncesDefense;
    
    private Etat etat;
    private int premierJoueur;
    
    
    /**
     * Constructeur de la table de jeu.
     * Doit initialiser le jeu de carte. Table à 0 joueurs par défaut.
     */
    public Table() {
    }
    
    
    /**
     * Permet d'ajouter un joueur à la table.
     * @param joueur doit déjà etre enregistré.
     * @return False si la table est déjà pleine, True sinon.
     */
    public boolean rejoindre(Joueur joueur) {
        return false;
    }
    
    /**
     * Commence la partie.
     * Associe à chaque joueur son ID et distribue les cartes.
     * @return False si le nombre de joueurs est insuffisant, True sinon.
     */
    public boolean commencer() {
        return false;
    }
    
    /**
     * Termine la partie.
     * Met à jour les stats des joueurs.
     */
    public void terminer() {
    }
    
    /**
     * Quitter la table.
     */
    public void quitter() {
    }
    
    /**
     * Affiche l'état de la table.
     */
    @Override
    public String toString() {
        return "";
    }
}
