/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.connexion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paul
 */
public class Database {
    private Map<String, List<String>> users;
    private final String usersFile = "src/main/java/game/connexion/database.txt";
    
    private static final String DATABASE_STRUCT = "LOGIN;MDP";
    private static final int MDP_INDEX = 0;
    
    public Database() {
        this.users = new HashMap();
        fillDatabase(users, usersFile);
    }

    /**
     * Add the user to database.
     * @param id
     * @param mdp
     * @return 
     */
    boolean addUser(String id, String mdp) { //TODO add verification
        if (!users.containsKey(id)) {
            List l = new LinkedList();
            
            // fields of a data input
            l.add(mdp);
            
            users.put(id, l);
            refreshDataFile();
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
        return users.containsKey(id) && users.get(id).get(MDP_INDEX).equals(mdp);
    }
    
    
    
    
    
    /**
     * Fill the given database with the data from the file.
     * @param database
     * @param dataFile 
     */
    private void fillDatabase(Map<String, List<String>> database, String dataFile) {
        String data = null;
        try {
            data = readDataFile(dataFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (data.length() > 0) {
            String[] rows = data.split("\n");
            for (int i = 1; i < rows.length; ++i) {
                String row = rows[i];
                if (row.length() > 0) {
                    String[] columns = row.split(";");
                    List l = new LinkedList();
                    database.put(columns[0], l);
                    for (int columnIndex = 1; columnIndex < columns.length; ++columnIndex) {
                        l.add(columns[columnIndex]);
                    }
                }
            }
        }
    }

    /**
     * Read the file and returns the String representing data.
     * @param dataFile
     * @return
     * @throws IOException 
     */
    private String readDataFile(String dataFile) throws IOException {
        FileInputStream fis = null;
        FileChannel fc = null;
        try {
            fis = new FileInputStream(new File(dataFile));
            fc = fis.getChannel();
            int size = (int)fc.size();
            ByteBuffer bBuff = ByteBuffer.allocate(size);
            fc.read(bBuff);
            bBuff.flip();
            byte[] tabByte = bBuff.array();
            return new String(tabByte);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (fis != null) fis.close();
            if (fc != null) fc.close();
        }
        return "";
    }
    
    
    
    
    
    /**
     * Write the whole input to the File.
     * @param dataFile
     * @param input 
     */
    private void writeDataFile(String dataFile, String input) {
        FileWriter fw;
        try {
            fw = new FileWriter(new File(dataFile));
            fw.write(input);
            fw.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Write database in File.
     */
    private void refreshDataFile() {
        String data = getDataString();
        writeDataFile(usersFile, data);
    }
    
    /**
     * Returns the adapted String that representes database.
     * @return 
     */
    private String getDataString() {
        String res = DATABASE_STRUCT + "\n";
        for (String id : users.keySet()) {
            res += id;
            List<String> l = users.get(id);
            for (String e : l)
                res += ";" + e;
            res += "\n";
        }
        return res;
    }
}
