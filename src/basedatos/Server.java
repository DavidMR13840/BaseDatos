/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Server {
    public ArrayList<BaseDatos> bases = new ArrayList<>();
    
    public BaseDatos selectbase(String pname){
        for(BaseDatos bd: bases){
            if(bd.getName().equals(pname)){
            return bd;
            }
        }
        return null;
    }
    
    public void updateBase(BaseDatos baseDatos){
        for(BaseDatos bd: bases){
            if(bd.equals(baseDatos)){
                bd = baseDatos;
            }
        }
    }
}
