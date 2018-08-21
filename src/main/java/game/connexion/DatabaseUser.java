/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.connexion;

import fr.connexion.AbstractDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author paul
 */
public class DatabaseUser extends AbstractDatabase {
    private static final int MDP_INDEX = 0;
    
    public DatabaseUser() {
        super("src/main/java/game/connexion/database.txt", "LOGIN;MDP", 2);
    }

    @Override
    protected String getDefaultColumn(int columnIndex) {
        switch (columnIndex) {
        case 1:
            return "changeme";
        default:
            return "";
        }
    }

    /**
     * Add the user to database.
     * @param id
     * @param mdp
     * @return 
     */
    boolean addUser(String id, String mdp) {
        if (id.contains(";") || mdp.contains(";"))
            return false;
        if (!data.containsKey(id)) {
            List l = new LinkedList();
            
            // fields of a data input
            l.add(mdp);
            
            data.put(id, l);
            refreshToDataFile();
            return true;
        }
        return false;
    }
    
    /**
     * Checks if the user is in database.
     * @param id
     * @param mdp
     * @return 
     */
    boolean contains(String id, String mdp) {
        return data.containsKey(id) && data.get(id).get(MDP_INDEX).equals(mdp);
    }
}
