/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tarot;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe d'un joueur humain.
 * Override pour IA.
 * Ne represente pas un Profil mais un joueur sur table.
 * @author paul
 */
public class Joueur {
    static Carte excuse = new Carte(0, Couleur.ATOUT);
    protected List<Carte> main;
    

    private void Constructor() {
        main = new ArrayList();
    }
    
    /**
     * Constructeur d'un compte déjà existant.
     * @param id du joueur enregistré
     * @param mdp
     * @throws IllegalArgumentException si le compte n'existe pas.
     */
    public Joueur(String id, String mdp) throws IllegalArgumentException {
        Constructor();
    }
    
    /**
     * Constructeur d'un compte non existant.
     * @param id
     * @param mdp
     * @param email 
     * @throws IllegalArgumentException si le pseudo est déjà pris.
     */
    public Joueur(String id, String mdp, String email)
            throws IllegalArgumentException {
        Constructor();
    }
    
    /**
     * Constructeur général.
     */
    public Joueur() {
        Constructor();
    }
    
    
    /**
     * Ajoute les score au profil et reinitialise les scores du joueur.
     * @param score 
     */
    public void deconnecter(Score score) {
    }
    
    
    /**
     * Renvoie une copy de la main.
     * Permet de ne pas modifier la main.
     * @return 
     */
    public List<Carte> getMain() {
        List<Carte> copy = new ArrayList();
        for (Carte carte : main) {
            copy.add(carte);
        }
        return copy;
    }
    
    /**
     * Renvoie la liste des cartes jouables en fonction de la premiere carte
     * jouee.
     * @param carteAppelee
     * @return 
     */
    public List<Carte> getAuthorized(Carte carteAppelee, int maxAtoutOnTable) {
        if (carteAppelee.equals(excuse))
            return getMain();
        
        List<Carte> copy = new ArrayList();
        Couleur couleurAppelee = carteAppelee.getCouleur();
        int valeurAppelee = carteAppelee.getValeur();
        if (couleurAppelee.equals(Couleur.ATOUT)) {
            if (hasAtoutSup(valeurAppelee))
                copy.addAll(getAtoutsSup(valeurAppelee));
            else if (hasAtout())
                copy.addAll(getAtouts());
            else
                return getMain();
        } else {
            if (hasCouleur(couleurAppelee))
                copy.addAll(getCouleurs(couleurAppelee));
            else if (hasAtoutSup(maxAtoutOnTable))
                copy.addAll(getAtoutsSup(maxAtoutOnTable));
            else if (hasAtout())
                copy.addAll(getAtouts());
            else
                return getMain();
        }
        if (main.contains(excuse))
            copy.add(excuse);
        
        return copy;
    }
    
    /**
     * Renvoie vrai si la main contient des atouts.
     * @return 
     */
    private boolean hasAtout() {
        for (Carte carte : main) {
            if (carte.getCouleur().equals(Couleur.ATOUT) && carte.getValeur() > 0)
                return true;
        }
        return false;
    }
    
    /**
     * Renvoie les atouts de la main.
     * @return 
     */
    private List<Carte> getAtouts() {
        List<Carte> copy = new ArrayList();
        for (Carte carte : main) {
            if (carte.getCouleur().equals(Couleur.ATOUT) && carte.getValeur() > 0)
                copy.add(carte);
        }
        return copy;
    }
    
    /**
     * Renvoie vrai si la main contient des atouts superieurs a max
     * @return 
     */
    private boolean hasAtoutSup(int max) {
        for (Carte carte : main) {
            if (carte.getCouleur().equals(Couleur.ATOUT) && carte.getValeur() > max)
                return true;
        }
        return false;
    }
    
    /**
     * Renvoie les atouts de la main superieurs a max
     * @return 
     */
    private List<Carte> getAtoutsSup(int max) {
        List<Carte> copy = new ArrayList();
        for (Carte carte : main) {
            if (carte.getCouleur().equals(Couleur.ATOUT) && carte.getValeur() > max)
                copy.add(carte);
        }
        return copy;
    }
    
    /**
     * Renvoie vrai si la main contient la couleur appelee.
     * @param couleurAppelee
     * @return 
     */
    private boolean hasCouleur(Couleur couleurAppelee) {
        for (Carte carte : main) {
            if (carte.getCouleur().equals(couleurAppelee))
                return true;
        }
        return false;
    }
    
    /**
     * Renvoie les cartes de la couleur de la main.
     * @param couleurAppelee
     * @return 
     */
    private List<Carte> getCouleurs(Couleur couleurAppelee) {
        List<Carte> copy = new ArrayList();
        for (Carte carte : main) {
            if (carte.getCouleur().equals(couleurAppelee))
                copy.add(carte);
        }
        return copy;
    }
    
    /**
     * Permet de couper le jeu de carte.
     * La coupe ne doit pas etre plus petite qu'un plis (4%).
     * @param pourcentage entier compris entre 7 et 93.
     * @return false si la coupe est interdite.
     */
    public boolean couper(Table table, int pourcentage) {
        return false;
    }
    
    /**
     * Demander la redistribution.
     * @return false si refus.
     */
    public boolean redistribuer() {
        return false;
    }
    
    /**
     * Fait une annonce.
     * 0 parole
     * 1 petite
     * 2 garde
     * 3 garde sans
     * 4 garde contre
     * @param table
     * @param mise annonce
     * @return false si l'annonce est interdite.
     */
    public boolean parler(Table table, int mise) {
        return false;
    }
    
    /**
     * Appel un roi.
     * @param table
     * @param couleur
     * @return false si l'appel est interdit.
     */
    public boolean appeler(Table table, Couleur couleur) {
        return false;
    }
    
    /**
     * Appel une carte autre qu'un roi.
     * @param table
     * @param carte
     * @return false si l'appel est interdit.
     */
    public boolean appeler(Table table, Carte carte) {
        return false;
    }
    
    /**
     * Fait une annonce.
     * Si période du chien, permet de valider le chien.
     * @param table
     * @param annonce
     * @return False si l'annonce est interdite.
     */
    public boolean annoncer(Table table, Annonce annonce) {
        return false;
    }
    
    /**
     * Joue une carte.
     * Si période de l'écart, permet de placer ou recuperer une carte.
     * @param table
     * @param carte
     * @return False si le coup est interdit.
     */
    public boolean jouer(Table table, Carte carte) {
        return false;
    }
}
