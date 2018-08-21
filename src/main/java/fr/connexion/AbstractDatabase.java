/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.connexion;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Allows to connect to a file.
 * The file must be construct by rows (means separated by '\n' character)
 * and by columns (separated by semicolon ';').
 * @author paul
 */
public abstract class AbstractDatabase {
    protected Map<String, List<String>> data;

    private final String usersFile;
    private final String DATABASE_STRUCT;
    private final int nb_total_columns;
    
    public AbstractDatabase(String filename, String databse_structure, int nb_total_columns) {
        this.usersFile = filename;
        this.DATABASE_STRUCT = databse_structure;
        this.nb_total_columns = nb_total_columns;
        this.data = new HashMap();
        refreshFromDataFile();
    }

    /**
     * Must returns the default value of the column if missing in the database.
     * @param columnIndex starts at 0 for key (don't need to give default value to key)
     * @return
     */
    protected abstract String getDefaultColumn(int columnIndex);

    protected int getIntOf(String string) {
        return Integer.valueOf(string);
    }

    protected String getStringOf(int num) {
        return String.valueOf(num);
    }






    /*******************  Read from file  *******************/

    /**
     * Load database from File.
     */
    public void refreshFromDataFile() {
        fillDatabase(data, usersFile, nb_total_columns);
    }

    /**
     * Fill the given database with the data from the file.
     * @param database
     * @param dataFile
     * @param nb_total_columns expected number of columns in database
     */
    private void fillDatabase(Map<String, List<String>> database, String dataFile, int nb_total_columns) {
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
                    for (int columnIndex = 1; columnIndex < nb_total_columns; ++columnIndex) {
                        if (columnIndex < columns.length)
                            l.add(columns[columnIndex].split("\r")[0]);
                        else
                            l.add(getDefaultColumn(columnIndex));
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





    /*******************  Write to file  *******************/

    /**
     * Write database in File.
     */
    public void refreshToDataFile() {
        String data = getDataString();
        writeDataFile(usersFile, data);
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
     * Returns the adapted String that representes database.
     * @return 
     */
    private String getDataString() {
        String res = DATABASE_STRUCT + "\n";
        for (String key : data.keySet()) {
            res += key;
            List<String> l = data.get(key);
            for (String e : l)
                res += ";" + e;
            res += "\n";
        }
        return res;
    }
}
