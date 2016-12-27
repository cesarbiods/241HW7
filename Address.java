/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc241hw07;

import java.util.ArrayList;

/**
 *
 * @author Cesar
 */
public abstract class Address {
    
    private String street;
    private int number;
    private String zipCode;
    private String type;
    private Account account;
    
    ArrayList<Meter> meters = new ArrayList<Meter>();
    
    public Address(String s, int n, String z, String t) {
        street = s;
        number = n;
        zipCode = z;
        type = t;
    }
    
    public String getStreet() {
        return street;
    }
    
    public int getNumber() {
        return number;
    }
    
    public String getBoth() {
        return Integer.toString(number) + " " + street;
    }
    
    public String getZipCode() {
        return String.valueOf(zipCode);
    }
    
    public abstract String getType();
    
//    public String toType() {
//        return type;
//    }
    
    public Meter[] getMeters() {
        Meter [] m = new Meter[meters.size()];
        m = meters.toArray(m);
        return m;
    }
    
    public boolean addMeter(Meter e) {
        if (e != null) {
            return meters.add(e);
        } else {
        return false;
    }
    }
    
    public boolean removeMeter(String id) {
        for (Meter m: meters) {
            if (m.getID().equals(id)) {
                return meters.remove(m);
            }
        } 
        return false;
    }
    
    public Account getAccount() {
        return account;
    }
    
    public void setAccount(Account a) {
        account = a;
    }
    
}
