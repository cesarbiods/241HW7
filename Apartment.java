/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc241hw07;

/**
 *
 * @author Cesar
 */
public class Apartment extends Address{
    
    private String unit;
    
    public Apartment(String s, int n, String z, String t, String u){
        super(s,n,z,t);
        unit = u;
    }
    
    public String getType() {
        return "apartment";
    }
    
    public void setUnit(String u) {
        unit = u;
    }
    
    public String getUnit() {
        return unit;
    }
}
