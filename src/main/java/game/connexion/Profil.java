/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.connexion;

import game.tarot.Joueur;
import game.tarot.Score;

/**
 *
 * @author paul
 */
public class Profil extends PrivateProfil {
    /**
     * Constructeur d'un compte déjà existant.
     * @param id du joueur enregistré
     * @param mdp
     * @throws IllegalArgumentException si le compte n'existe pas.
     */
    public Profil(String id, String mdp) throws IllegalArgumentException {
        SingletonJoueurs singletonJoueurs = SingletonJoueurs.getOccurence();
        if (!singletonJoueurs.contains(id, mdp))
            throw new IllegalArgumentException("Wrong pseudo or password.");
        this.score = singletonJoueurs.getScore(id, mdp);
    }
    
    /**
     * Constructeur d'un compte non existant.
     * @param id
     * @param mdp
     * @param email 
     * @throws IllegalArgumentException si le pseudo est déjà pris.
     */
    public Profil(String id, String mdp, String email)
            throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
