/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.connexion;

import game.tarot.Score;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author paul
 */
public class SingletonJoueurs {
    private static SingletonJoueurs singleton;
    
    private final DatabaseUser database;
    private final Set<String> idConnected;
    
    
    private SingletonJoueurs() {
        idConnected = new TreeSet();
        database = new DatabaseUser();
    }
    
    public final static SingletonJoueurs getOccurence() {
        if (singleton == null)
            singleton = new SingletonJoueurs();
        return singleton;
    }
    
    
    public boolean contains(String id, String mdp) {
        if (database.contains(id, mdp) && !idConnected.contains(id)) {
            idConnected.add(id);
            return true;
        }
        return false;
    }
    
    public void disconnect(String id) {
        idConnected.remove(id);
    }
    
    public boolean createUser(String id, String mdp) {
        return database.addUser(id, mdp);
    }

    public Score getScore(String id, String mdp) {
        //TODO
        return new Score();
    }
}
