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
public abstract class Meter {
    
    private String id;
    private String brand;
    private String type;
    private Address home;
    private String location;
    
    ArrayList<MeterReading> readings = new ArrayList<MeterReading>();
    
    public Meter(String i, String b, String t) {
        id = i;
        brand = b;
        type = t;
    }
    
    public String getID() {
        return String.valueOf(id);
    }
    
    public String getBrand() {
        return brand;
    }
    
    public abstract String getType();
    
    public String toType(){
        return type;
    }
    
    public Address getAddress() {
        return home;
    }
    
    public MeterReading[] getReadings() {
        MeterReading [] a = new MeterReading[readings.size()];
        a = readings.toArray(a);
        return a;
    }
    
    public boolean addReading(MeterReading m) {
        if (m != null) {
            return readings.add(m);
        } else {
            return false;
        }
    }
    
    public MeterReading getCurrentReading() {
        if (readings.isEmpty()) {
            return null;
        } else {
            return readings.get(readings.size()-1);
        }
            
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(Address a, String l) {
        home = a;
        location = l;
    }
    
}
