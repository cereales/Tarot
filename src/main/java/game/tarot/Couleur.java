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
public enum Couleur {
    ATOUT,
    PIQUE   ("P"),
    COEUR   ("O"),
    TREFLE  ("T"),
    CARREAU ("A");
    
    private String abreviation;
    
    Couleur(String abreviation) {
        this.abreviation = abreviation;
    }
    
    Couleur() {
        this.abreviation = this.name();
    }
    
    @Override
    public String toString() {
        return this.abreviation;
    }
}
