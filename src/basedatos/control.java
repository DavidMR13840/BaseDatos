/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package basedatos;

/**
 *
 * @author David
 */
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;


public class control {
    
    public static void main(String[] args) {
                
        BaseDatos baseDatos = null;
        Gson gson = new Gson();
  
//        try {
//            FileWriter file = new FileWriter("Prueba.json");
//                file.write(jsonString);
//                file.flush();
//        } catch (IOException ex) {
//            Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
//        }
        BufferedReader br = null;
        try{
            br= new BufferedReader(new FileReader("Prueba.json"));
             baseDatos = gson.fromJson(br, BaseDatos.class);
//            String newjson = gson.toJson(baseDatos);
//            System.out.println("2 " + newjson);
            
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        Server server = new Server();
        server.bases.add(baseDatos);
        String toJson = gson.toJson(server);
                try {
            FileWriter file = new FileWriter("Server.json");
                file.write(toJson);
                file.flush();
        } catch (IOException ex) {
            Logger.getLogger(control.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
