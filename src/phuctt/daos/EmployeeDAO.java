/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuctt.daos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Vector;

/**
 *
 * @author Thien Phuc
 */
public class EmployeeDAO {
    private String filename = "data.txt";
    
    //load data
    public Vector loadData() {
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        Vector record = new Vector();
        
        try {
            f = new File(filename);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            
            String str;
            Vector<String> e;
            while ((str = br.readLine()) != null) {
                String[] subStr = str.split(";");
                String code = subStr[0].trim();
                String name = subStr[1].trim();
                String dept = subStr[2].trim();
                String salary = subStr[3].trim();
                
                e = new Vector<>();
                e.add(code);
                e.add(name);
                e.add(dept);
                e.add(salary);
                record.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (fr != null) fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return record;
    }
    
    //save data
    public void saveData(Vector v) {
        File f = null;
        PrintWriter pw = null;
        
        try {
            f = new File(filename);
            pw = new PrintWriter(f);
            
            for (int i = 0; i < v.size(); i++) {
                String str = "";
                for (int j = 0; j < 4; j++) {
                    str += ((Vector<String>) v.get(i)).get(j) + ((j != 3) ? ";" : "");
                }
                pw.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pw != null) pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
