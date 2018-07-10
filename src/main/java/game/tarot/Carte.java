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
        this.valeur = valeur;
        this.couleur = couleur;
    }
    
    
    public Couleur getCouleur() {
        return this.couleur;
    }
    
    public int getValeur() {
        return this.valeur;
    }
    
    public String getNom() {
        if (this.couleur.equals(Couleur.ATOUT))
            return this.valeur + "D'ATOUT";
        else
            if (this.valeur > 10)
                switch (this.valeur) {
                    case 11:
                        return "VALET DE " + this.couleur.name();
                    case 12:
                        return "CAVALIER DE " + this.couleur.name();
                    case 13:
                        return "DAME DE " + this.couleur.name();
                    default:
                        return "ROI DE " + this.couleur.name();
                }
            else
                return this.valeur + " DE " + this.couleur.name();
    }
    
    @Override
    public boolean equals(Object carte) {
        return ((Carte) carte).valeur == this.valeur
                && ((Carte) carte).couleur == this.couleur;
    }

    @Override
    public String toString() {
        if (this.couleur.equals(Couleur.ATOUT)) {
            if (this.valeur > 9)
                return "" + valeur;
            else
                if (this.valeur == 0)
                    return "**";
                else
                    return "0" + valeur;
        } else {
            if (this.valeur > 9)
                switch (this.valeur) {
                    case 10:
                        return "0" + this.couleur;
                    case 11:
                        return "V" + this.couleur;
                    case 12:
                        return "C" + this.couleur;
                    case 13:
                        return "D" + this.couleur;
                    default:
                        return "R" + this.couleur;
                }
            else
                return this.valeur + "" + this.couleur;
        }
    }
}
