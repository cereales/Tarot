package game.connexion;

import game.tarot.Joueur;
import game.tarot.Score;

/**
 * Profil of a IA.
 */
public class Computer extends PrivateProfil {
    public Computer() {
        this.score = new Score();
    }
}
