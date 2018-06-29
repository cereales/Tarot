/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tarot;

/**
 *
 * @author paul
 */
public class Carte {
    private Couleur couleur;
    private int valeur;
    
    
    /**
     * Constructeur d'une carte
     * @param valeur comprise entre 1 et 14 ou 0 et 21, selon la couleur
     * @param couleur 
     */
    public Carte(int valeur, Couleur couleur) {
    }

    @Override
    public String toString() {
        return "R0";
    }
}
