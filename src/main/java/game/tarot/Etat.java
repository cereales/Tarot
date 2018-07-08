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
public enum Etat {
    INSCRIPTION ("descr"),
    INTERRUPTION,
    ABANDON,
    FIN,
    DISTRIBUTION,
    MISES,
    APPEL,
    PLIS,
    AMASSER,
    POINTS;
    
    private String descr;
    
    Etat(String descr) {
        this.descr = descr;
    }
    
    Etat() {
        this.descr = this.name();
    }
    
    @Override
    public String toString() {
        return this.descr;
    }
}
