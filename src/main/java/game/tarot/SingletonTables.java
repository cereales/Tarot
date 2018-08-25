/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.tarot;

import game.connexion.Profil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author paul
 */
public class SingletonTables {
    private static SingletonTables singleton;
    
    private final Map<Integer, Table> tables;
    private Integer nextIndex = 1;
    
    
    private SingletonTables() {
        tables = new HashMap();
    }
    
    public static final SingletonTables getOccurence() {
        if (singleton == null)
            singleton = new SingletonTables();
        return singleton;
    }
    
    
    public Table createTable() {
        Table table = new Table();
        tables.put(nextIndex, table);
        ++nextIndex;
        return table;
    }
    
    public void deleteTable(Integer index) {
        tables.remove(index);
    }
    
    public Table getTable(Integer id) {
        return tables.get(id);
    }
    
    public Integer getId(Table table) {
        for (Entry<Integer, Table> entry : tables.entrySet())
            if (entry.getValue().equals(table))
                return entry.getKey();
        return null;
    }
    
    
    /**
     * Returns the string of tables where profil is not connected.
     * @param profil
     * @return 
     */
    public String getStringTablesDisconnect(Profil profil) {
        return getStringTablesDisconnect(profil, true);
    }
    
    /**
     * Returns the string of tables where profil is connected.
     * @param profil
     * @return 
     */
    public String getStringTablesConnect(Profil profil) {
        return getStringTablesDisconnect(profil, false);
    }
    
    private String getStringTablesDisconnect(Profil profil, boolean disconnect) {
        Map<Integer, Table> tables = getTablesDisconnect(profil, disconnect);
        String res = "[";
        ArrayList<Integer> tmp = new ArrayList(tables.keySet());
        tmp.sort(null);
        for (Integer id : tmp)
            if (disconnect)
                res += id + ", ";
            else
                res += tables.get(id) + ", ";
        if (res.length() >= 2)
            return res.substring(0, res.length() - 2) + "]";
        else
            return "[]";
    }

    private Map<Integer, Table> getTablesDisconnect(Profil profil, boolean disconnect) {
        Map<Integer, Table> res = new HashMap();
        for (Entry<Integer, Table> entry : tables.entrySet())
            if (disconnect ^ entry.getValue().isConnected(profil.getJoueur()))
                res.put(entry.getKey(), entry.getValue());
        return res;
    }
}
