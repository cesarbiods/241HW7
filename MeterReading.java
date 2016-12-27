/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc241hw07;

import java.time.LocalDateTime;

/**
 *
 * @author Cesar
 */
public class MeterReading {
    
    private double reading;
    private LocalDateTime date;
    //is this a string?
    private String flag;
    private Meter meter;
    
    public double getReading() {
        return reading;
    }
    //change to getDate if anything
    public LocalDateTime getDateTime() {
        return date;
    }
    
    public String getFlag() {
        return flag;
    }
    
    public Meter getMeter() {
        return meter;
    }
    
    public MeterReading(double r, LocalDateTime d, String f, Meter m) {
        reading = r;
        date = d;
        flag = f;
        meter = m;
    }
}
