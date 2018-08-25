/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tarot;

import game.connexion.PublicProfil;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.Math.random;


/**
 *
 * @author paul
 */
public class Table {
    private List<Joueur> joueurs;
    private List<PublicProfil> profils;
    private List<Score> scores; // scores depuis la connexion
    private JeuCartes jeu;
    
    private int appelant;
    private int appelé;
    private int mise;
    private Set<Annonce> annoncesAttaque;
    private Set<Annonce> annoncesDefense;
    
    private Etat etat; // Use setter inside class
    private int premierJoueur;

    private Etat nextEtatQuery = null;
    
    
    /**
     * Constructeur de la table de jeu.
     * Doit initialiser le jeu de carte. Table à 0 joueurs par défaut.
     */
    protected Table() {
        joueurs = new ArrayList();
        profils = new ArrayList();
        scores = new ArrayList();
        jeu = new JeuCartes();
        etat = Etat.INSCRIPTION;
    }
    
    
    /**
     * Permet d'ajouter un joueur à la table.
     * @param joueur doit déjà etre enregistré.
     * @return False si la table est déjà pleine ou le joueur deja dessus, True sinon.
     */
    public boolean rejoindre(PublicProfil profil, Joueur joueur) {
        if (!etat.equals(Etat.INSCRIPTION) || joueurs.size() >= 5 || joueurs.contains(joueur)) // joueur est plus privé que le profil public
            return false;
        jeu.addJoueur(joueur);
        joueurs.add(joueur);
        profils.add(profil);
        return true;
    }
    
    /**
     * Commence la partie.
     * Associe à chaque joueur son ID et distribue les cartes.
     * @return False si le nombre de joueurs est insuffisant, True sinon.
     */
    public boolean commencer() {
        if (!etat.equals(Etat.INSCRIPTION))
            return false;
        int distibuteur = (int) (random() * joueurs.size());
        if (!jeu.distribuer(distibuteur))
            return false;
        for (int i = 0; i < joueurs.size(); ++i)
            scores.add(new Score());
        setEtat(Etat.MISES);
        premierJoueur = JeuCartes.nextCircleIndex(distibuteur, 0, joueurs.size());
        return true;
    }

    /**
     * Demande d'interruption de la part du joueur.
     * @param joueur
     * @return false si la demande n'est pas prise en compte.
     */
    public boolean interrompre(Joueur joueur) {
        return setNextEtatQuery(Etat.INTERRUPTION);
    }

    /**
     * Reprend le cours du jeu.
     * @return false si le jeu ne peut pas etre repris.
     */
    public boolean reprendre() {
        if (!etat.equals(Etat.INTERRUPTION))
            return false;
        setEtat(null);
        return true;
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
     * Send query to set the next state.
     * @param nextEtatQuery
     * @return false if the next state is already determined.
     */
    private boolean setNextEtatQuery(Etat nextEtatQuery) {
        if (this.nextEtatQuery != null)
            return false;
        this.nextEtatQuery = nextEtatQuery;
        return true;
    }

    /**
     * Setter of etat.
     * Change state, even if it is not the one asked for.
     * Use this method inside Table class.
     * @param etat
     */
    private void setEtat(Etat etat) {
        boolean canSetState = setNextEtatQuery(etat);
        this.etat = nextEtatQuery;
        if (canSetState)
            nextEtatQuery = null;
        else
            nextEtatQuery = etat;

        if (this.etat.equals(Etat.INTERRUPTION))
            saveTable();
    }

    /**
     * Save the game.
     * @precond Need to be in INTERRUPTION state.
     */
    private void saveTable() {
        //TODO
    }



    /**
     * Affiche l'état de la table.
     */
    @Override
    public String toString() {
        if (nextEtatQuery == null)
            return "* TABLE " + getId() + " [" + etat + "]";
        else
            return "* TABLE " + getId() + " [" + etat + " -> " + nextEtatQuery + "]";
    }

    public boolean isConnected(Joueur joueur) {
        return joueurs.contains(joueur);
    }

    public Integer getId() {
        return SingletonTables.getOccurence().getId(this);
    }
    
    public Etat getEtat() {
        return etat;
    }
}
