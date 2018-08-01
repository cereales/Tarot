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
public class Carte implements Comparable {
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
    
    /**
     * Renvoie le nombre de points de la carte en demis.
     * Exemple : getPoints(dame de pique) = 7 demis (= 3.5)
     * @return 
     */
    public int getPoints() {
        if (couleur.equals(Couleur.ATOUT)) {
            if (JeuCartes.bouts.contains(this))
                return 9;
            else
                return 1;
        }
        switch (valeur) {
            case 14:
                return 9;
            case 13:
                return 7;
            case 12:
                return 5;
            case 11:
                return 3;
            default:
                return 1;
        }
    }
    
    /**
     * Used for compare force of Cartes.
     * this is the carte of the right Couleur.
     * That is why this function is not reversible.
     * @param o
     * @return true if is stronger than o.
     */
    public boolean winsAgainst(Carte o) {
        if (equals(JeuCartes.bouts.get(0)))
            return false;
        if (o.equals(JeuCartes.bouts.get(0)))
            return true;
        if (couleur.equals(Couleur.ATOUT))
            return (! o.couleur.equals(Couleur.ATOUT)
                    || o.valeur < valeur);
        return (! couleur.equals(o.couleur) && ! o.couleur.equals(Couleur.ATOUT)
                || couleur.equals(o.couleur) && valeur > o.valeur);
    }
    
    /**
     * Used for sort.
     * Assumes that enum is {0, 1, 2, ...} and returns (this - other).
     * @param other
     * @return this minus other
     */
    @Override
    public int compareTo(Object other) {
        Carte o = (Carte) other;
        return this.couleur.compareTo(o.couleur) * 22
                + new Integer(valeur).compareTo(o.valeur);
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
