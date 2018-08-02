package game.connexion;

import game.tarot.Joueur;

public abstract class PrivateProfil extends PublicProfil {
    private Joueur joueur;

    public PrivateProfil() {
        this.joueur = new Joueur();
    }
    //TODO : disable cast of PublicProfil to PrivateProfil


    public Joueur getJoueur() {
        return joueur;
    }
}
