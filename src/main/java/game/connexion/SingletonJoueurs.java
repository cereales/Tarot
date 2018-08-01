/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.connexion;

import game.tarot.Score;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author paul
 */
public class SingletonJoueurs {
    private static SingletonJoueurs singleton;
    
    private Map<String, String> profils;
    private Set<String> idConnected;
    
    
    private SingletonJoueurs() {
        idConnected = new TreeSet();
        profils = new HashMap();
        profils.put("bob", "mdp1");
        profils.put("paul", "mdp2");
        profils.put("pierre", "mdp2");
        profils.put("jacques", "mdp2");
    }
    
    public final static SingletonJoueurs getOccurence() {
        if (singleton == null)
            singleton = new SingletonJoueurs();
        return singleton;
    }
    
    
    public boolean contains(String id, String mdp) {
        if (profils.containsKey(id) && profils.get(id).equals(mdp) && !idConnected.contains(id)) {
            idConnected.add(id);
            return true;
        }
        return false;
    }

    public Score getScore(String id, String mdp) {
        //TODO
        return new Score();
    }
}
