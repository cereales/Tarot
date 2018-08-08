package game.connexion;

import game.tarot.Joueur;

public abstract class PrivateProfil extends PublicProfil {
    private Joueur joueur;
    protected String pseudo;
    
    public PrivateProfil() {
        this.joueur = new Joueur();
    }
    //TODO : disable cast of PublicProfil to PrivateProfil


    public Joueur getJoueur() {
        return joueur;
    }
    
    
    public boolean isConnected() {
        return joueur != null;
    }
    
    /** A computer cannot be disconnected.
     */
    public void disconnect() {
        joueur = null;
        SingletonJoueurs.getOccurence().disconnect(this.pseudo);
    }
}
