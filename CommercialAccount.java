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
public class CommercialAccount extends Account implements Constants{
    
    public CommercialAccount(String a, Customer b) {
        super(a,b);
        this.setBalance(0);
    }
    
    public void updateBalance() {
        
        double readingsum = 0;
        
        for (Address a: homes) {
            for (Meter m: a.getMeters()) {
                for (MeterReading mr: m.getReadings()) {
                    readingsum += mr.getReading();
                }
            }
        }
        this.setBalance(readingsum * commercialUnitRate);
    }
}
