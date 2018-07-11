/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tarot;

import static java.lang.Math.random;
import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.sort;

/**
 *
 * @author paul
 */
public class JeuCartes {
    private int nbJoueurs;
    private static int NB_CARTES = 78;
    private static List<Carte> bouts = new ArrayList();
    private int nbCartesChien = 0;
    
    private List<Carte> paquet; // la carte indicée 0 est celle du dessus du paquet
    private List<Joueur> joueurs; // Pour stocker les mains et les modifier.
    private List<Carte> table; // Pour le chien et le jeu.
    private List<Carte> plisAttaque;
    private List<Carte> plisDefense;
    
    
    public JeuCartes() {
        paquet = new ArrayList();
        joueurs = new ArrayList();
        table = new ArrayList();
        plisAttaque = new ArrayList();
        plisDefense = new ArrayList();
        nbJoueurs = 0;

        List<Carte> tmp = new ArrayList();
        for (Couleur couleur : Couleur.values()) {
            int nbCartes = 14;
            if (couleur.equals(Couleur.ATOUT)) {
                nbCartes = 21;
                tmp.add(new Carte(0, couleur));
            }
            for (int valeur = 1; valeur <= nbCartes; ++valeur) {
                tmp.add(new Carte(valeur, couleur));
            }
        }
        for (int index = NB_CARTES - 1; index >= 0; --index) {
            paquet.add(tmp.remove((int) (random() * index)));
        }
        
        //TODO : set static, change init
        this.bouts.add(new Carte(0, Couleur.ATOUT));
        this.bouts.add(new Carte(1, Couleur.ATOUT));
        this.bouts.add(new Carte(21, Couleur.ATOUT));
    }
    
    /**
     * Ajoute les joueurs aux jeu.
     * Doit etre appelé apres la construction.
     * @param joueur
     * @return id du joueur ajouté.
     */
    public int addJoueur(Joueur joueur) {
        joueurs.add(joueur);
        return nbJoueurs++;
    }
    
    /**
     * Permet de couper le jeu de carte.
     * De l'aléatoire est ajouté.
     * @param pourcentage entier compris entre 7 et 93. Pourcentage du paquet supérieur.
     * @return false si les cartes ne sont pas regroupees dans le paquet.
     */
    public boolean couper(int pourcentage) {
        if (!enPaquet())
            return false;
        
        assert(7 <= pourcentage && pourcentage <= 93);
        int coupe;
        do {
            coupe = (int) (pourcentage + (20 * random() - 10));
        } while (coupe < 7 && 93 > coupe);
        
        for (int i = 0; i < paquet.size() * coupe / 100; ++i) {
            paquet.add(paquet.remove(0));
        }
        return true;
    }
    
    private boolean enPaquet() {
        if (!table.isEmpty() || !plisAttaque.isEmpty()
                || !plisDefense.isEmpty())
            return false;
        for (Joueur joueur: joueurs) {
            if (!joueur.main.isEmpty())
                return false;
        }
        return true;
    }
    
    /**
     * Distribue les cartes.
     * @return false si le nombre de joueur est interdit.
     */
    public boolean distribuer(int distributeur) {
        if (!enPaquet())
            return false;
        
        switch (nbJoueurs) {
            case 4:
                nbCartesChien = 6;
                break;
            case 5:
                nbCartesChien = 3;
                break;
            default:
                return false;
        }
        int[] toursChien = new int[nbCartesChien];
        do {
            for (int i = 0; i < nbCartesChien; ++i) {
                toursChien[i] = (int) ((random() * (NB_CARTES - 6 - nbCartesChien)) / 3 + 1) * 3;
            }
            sort(toursChien);
        } while (!planChienCorrect(toursChien));
        int decalage = 1;
        for (int i = 1; i < nbCartesChien; ++i) {
            toursChien[i] += decalage++;
            if (toursChien[i - 1] == toursChien[i])
                decalage++;
        }
        
        int joueur = nextCircleIndex(distributeur, 0, nbJoueurs);
        int indexTourChien = 0;
        for (int tour = 0; tour < NB_CARTES; ++tour) {
            if (toursChien[indexTourChien] == tour) { // carte au chien
                indexTourChien = nextCircleIndex(indexTourChien, 0, nbCartesChien);
                table.add(paquet.remove(0));
            } else {
                joueurs.get(joueur).main.add(paquet.remove(0));
                joueurs.get(joueur).main.add(paquet.remove(0));
                joueurs.get(joueur).main.add(paquet.remove(0));
                joueur = nextCircleIndex(joueur, 0, nbJoueurs);
                tour += 2;
            }
            //System.out.println(this);
        }
        return true;
    }
    
    /**
     * Returns the next value of currentValue.
     * This value might be in [firstIndex, lastIndex).
     * @param currentValue the value to increment of 1.
     */
    private int nextCircleIndex(int currentValue, int firstIndex, int lastIndex) {
        assert(firstIndex < lastIndex);
        ++currentValue;
        while (currentValue >= lastIndex) {
            currentValue -= lastIndex - firstIndex;
        }
        return currentValue;
    }
    
    /**
     * Verifie que toutes les cartes sont distribuées sur des tours differents.
     * Un maximum de 2 cartes est toleré sur le meme tour.
     * @param plan tableau trié
     * @return si le tableau est correct
     */
    private boolean planChienCorrect(int[] plan) {
        assert(plan[0] != 0);
        int tolerance = 2;
        for (int i = 1; i < plan.length ; ++i) {
            if (plan[i - 1] == plan[i]) {
                if (--tolerance == 0)
                    return false;
            } else
                tolerance = 2;
        }
        return true;
    }
    
    /**
     * Rassemble les cartes.
     * @return false si les cartes ne sont pas toutes dans les plis.
     */
    public boolean ramasser() {
        if (plisAttaque.size() + plisDefense.size() != NB_CARTES)
            return false;
        //TODO
        return true;
    }
    
    /**
     * Abandonne la partie en cours et ramasse les cartes.
     */
    public void abandonner() {
        //TODO
    }
    
    
    /**
     * Placer les cartes dans la main du joueur.
     * @param joueur 
     */
    public void donnerChien(int joueur) {
        for (Carte carte : table) {
            joueurs.get(joueur).main.add(carte);
        }
        table.removeAll(table);
    }
    
    /**
     * Mettre les cartes de la table dans les plis de l'equipe.
     * Sert aussi pour le chien.
     * @param attaque : si true, les point vont à l'attaque. Sinon à la défense.
     */
    public void ammasserCartes(boolean attaque) {
        List<Carte> dest = (attaque) ? plisAttaque : plisDefense;
        for (int index = table.size() - 1; index >= 0 ; --index) {
            dest.add(table.remove(0));
        }
    }
    
    /**
     * L'attaque fait son écart.
     * Si la carte est déjà dans le chien,
     * elle est remise dans la main du joueur.
     * Le nombre de cartes n'est pas controlé.
     * @param carte doit etre à la bonne personne
     * @return false si la carte n'appartient pas au joueur.
     */
    public boolean faireEcart(Carte carte, int joueur) {
        if (joueurs.get(joueur).main.contains(carte)) {
            joueurs.get(joueur).main.remove(carte);
            table.add(carte);
        } else if (table.contains(carte)) {
            table.remove(carte);
            joueurs.get(joueur).main.add(carte);
        } else
            return false;
        return true;
    }
    
    /**
     * Valide l'écart.
     * @return false si le nombre de cartes n'est pas le bon
     *         ou si les cartes mises sont interdites.
     */
    public boolean validerEcart() {
        if (table.size() != nbCartesChien)
            return false;
        
        if (!hasWrongChien(false, false))
            return true;
        else {
            for (Carte carte : joueurs.get(0).main) {} //TODO
        }
        return true;
    }
    
    /**
     * Renvoie true si le chien contient des cartes a 4.5 points.
     * @return 
     */
    private boolean hasWrongChien(boolean authorizeAtouts, boolean authorizedKings) {
        for (Carte carte : table) {
            if (carte.getCouleur().equals(Couleur.ATOUT) || carte.getValeur() == 14)
                return true;
        }
        return false;
    }
    
    
    /**
     * Place une carte sur la table. Le premier appel initialise la couleur
     * demandée.
     * Pas de controle sur le respect des tours.
     * @param carte doit appartenir au joueur dont c'est le tour.
     * @return false si l'action est interdite.
     */
    public boolean jouer(int joueur, Carte carte) {
        if (table.isEmpty()) { // premier appel
            if (!joueurs.get(joueur).main.remove(carte))
                return false;
            table.add(carte);
        } else { // suivre a l'appel
            if (!joueurs.get(joueur).getAuthorized(table.get(0), getMaxAtoutOnTable()).contains(carte))
                return false;
            joueurs.get(joueur).main.remove(carte);
            table.add(carte);
        }
        return true;
    }
    
    /**
     * Renvoie la valeur du plus grand atout sur la table.
     * @return 0 si pas d'atout.
     */
    private int getMaxAtoutOnTable() {
        int val = 0;
        for (Carte carte : table) {
            if (carte.getCouleur().equals(Couleur.ATOUT) && carte.getValeur() > val)
                val = carte.getValeur();
        }
        return val;
    }
    
    /**
     * Ressoud le plis précédant et renvoie le numéro du prochain joueur à
     * jouer.
     * Vérifie que le nombre de cartes tombées correspond au nombre de joueurs.
     * Résoudre le plis signifie gérer le cas de l'excuse.
     * @return le numéro du prochain joueur, cad le gagnant du plis.
     *         -1 si erreur.
     */
    public int nouveauLanceur() {
        //TODO
        return -1;
    }
    
    
    /**
     * Compte les points des plis de l'attaque.
     * @return le resultat.
     */
    public int compterPoints() {
        //TODO
        return -1;
    }
    

    /**
     * Only for debug.
     */
    @Override
    public String toString() {
        String res = "";
        String sep = ".----------.-------------------------------------------------------------.";

        res += sep + '\n';
        res += "|  PAQUET  |" + addCartesOf(paquet, sep) + '\n';
        for (int n = 0; n < nbJoueurs; ++n) {
            res += "|    J" + (n + 1) + "    |" + addCartesOf(joueurs.get(n).main, sep) + '\n';
        }
        res += "|  TABLE   |" + addCartesOf(table, sep) + '\n';
        res += "| PLIS ATT |" + addCartesOf(plisAttaque, sep) + '\n';
        res += "| PLIS DEF |" + addCartesOf(plisDefense, sep);

        return res;
    }

    /**
     * Returns what is in the paquet.
     */
    private String addCartesOf(List<Carte> paquet, String sep) {
        String res = " ";
        int i = 0;
        while (i < paquet.size()) {
            if (i % 20 == 0 && i != 0) res += "|\n|          | ";
            res += paquet.get(i++) + " ";
        }
        for (int tmp = 0; tmp < 19 - (i-1) % 20; ++tmp) res += "   ";
        res += "|\n" + sep;
        return res;
    }
}
