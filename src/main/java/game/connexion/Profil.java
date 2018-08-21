/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.connexion;

import game.tarot.Joueur;
import game.tarot.Score;
import game.tarot.SingletonTables;
import game.tarot.Table;

/**
 *
 * @author paul
 */
public class Profil extends PrivateProfil {
    private Table currentTable;
    
    private static enum state {MAIN, TABLES, JEUX, TABLE_CONNX, JEU_CONNX, JEU};
    private state cmd_state;
    private static final String cmd = "Commandes :\n\t" + "0: help\n\t";
    private static final String cmd_main = cmd
            + "1: Exit\n\t2: voir tables\n\t3: jeux en cours\n\t4: scores";
    private static final String cmd_tables = cmd
            + "1: retour\n\t2: connexion\n\t3: creer table";
    private static final String cmd_jeux = cmd
            + "1: retour\n\t2: connexion";
    private static final String cmd_jeu = cmd
            + "1: deconnecter\n\t2: abandonner";
    
    
    /**
     * Constructeur d'un compte déjà existant.
     * @param id du joueur enregistré
     * @param mdp
     * @throws IllegalArgumentException si le compte n'existe pas.
     */
    public Profil(String id, String mdp) throws IllegalArgumentException {
        this.cmd_state = state.MAIN;
        SingletonJoueurs singletonJoueurs = SingletonJoueurs.getOccurence();
        if (!singletonJoueurs.contains(id, mdp))
            throw new IllegalArgumentException("Wrong pseudo or password.");
        this.score = singletonJoueurs.getScore(id, mdp);
        this.pseudo = id;
    }
    
    /**
     * Constructeur d'un compte non existant.
     * @param id
     * @param mdp
     * @param email 
     * @throws IllegalArgumentException si le pseudo est déjà pris.
     */
    public Profil(String id, String mdp, String email)
            throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    /**
     * Return the next question.
     * @param input is the answer of previous question.
     * @return 
     */
    public String execute(int input) {
        switch (cmd_state) {
        case MAIN:
            switch (input) {
            case 1:
                this.disconnect();
                return "Disconnected.";
            case 2:
                cmd_state = state.TABLES;
                return execute(0);
            case 3:
                cmd_state = state.JEUX;
                return execute(0);
            case 4:
                return "Votre score : " + this.score.toString();
            default:
                return cmd_main;
            }
        case TABLES:
            switch (input) {
            case 1:
                cmd_state = state.MAIN;
                return execute(0);
            case 2:
                cmd_state = state.TABLE_CONNX;
                return "Connexion à quelle table ?";
            case 3:
                return "Table ID" + SingletonTables.getOccurence().createTable().getId().toString()
                        + " crée.\n" + execute(0); // assumes reading left to right
            default:
                return cmd_tables + "\n> TABLES "
                        + SingletonTables.getOccurence().getStringTablesDisconnect(this);
            }
        case TABLE_CONNX:
            Table table = SingletonTables.getOccurence().getTable(input);
            try {
                cmd_state = state.TABLES;
                if (!table.rejoindre(this, getJoueur()))
                    throw new IllegalArgumentException("Table is already full.");
                cmd_state = state.JEUX;
                return "Succes. Vous faites partie des joueurs de la table ID" + input + ".\n"
                        + execute(0);
            } catch (NullPointerException e) {
                return "Echec. La table ID" + input + " n'existe pas.\n" + execute(0);
            } catch (IllegalArgumentException e) {
                return "Echec. Vous êtes déjà sur cette table ou bien elle est complete.\n" + execute(0);
            }
        case JEUX:
            switch (input) {
            case 1:
                cmd_state = state.MAIN;
                return execute(0);
            case 2:
                cmd_state = state.JEU_CONNX;
                return "Jouer sur quelle table ?";
            default:
                return cmd_jeux + "\n> JEUX "
                        + SingletonTables.getOccurence().getStringTablesConnect(this);
            }
        case JEU_CONNX:
            currentTable = SingletonTables.getOccurence().getTable(input);
            try {
                cmd_state = state.JEUX;
                if (!currentTable.isConnected(getJoueur()))
                    throw new IllegalArgumentException("Not connected.");
            } catch (NullPointerException e) {
                return "Echec. La table ID" + input + " n'existe pas.\n" + execute(0);
            } catch (IllegalArgumentException e) {
                return "Echec. Vous ne jouez pas sur la table ID" + input + ".\n" + execute(0);
            }
            cmd_state = state.JEU;
            return execute(0);
        case JEU:
            String cmd_jeuExtended = ""; // no default statement for switch(input): see general cases next
            switch (currentTable.getEtat()) {
            case INSCRIPTION:
                cmd_jeuExtended = "\n\t3: ajouter ordi\n\t4: commencer";
                switch (input) {
                case 3:
                    Computer ordi = new Computer();
                    if (currentTable.rejoindre(ordi, ordi.getJoueur()))
                        return "Ordi ajouté avec succes.";
                    else
                        return "Echec de l'ajout de l'ordi.";
                case 4:
                    if (currentTable.commencer())
                        return "Partie commencée.";
                    else
                        return "Echec. Pas assez de joueurs.";
                }
                break;
            case INTERRUPTION: //TODO
            case ABANDON:
            case FIN:
            case DISTRIBUTION:
            case MISES:
            case APPEL:
            case PLIS:
            case AMASSER:
            case POINTS:
            default:
                return "ERROR: never reached";
            }
            switch (input) {
            case 2:
                //TODO + ne pas break
            case 1:
                cmd_state = state.JEUX;
                currentTable = null;
                return execute(0);
            case 3:
            default:
                return cmd_jeu + cmd_jeuExtended + "\n> "
                        + currentTable;
            }
        default:
            return "ERROR: never reached";
        }
    }
}
