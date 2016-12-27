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
public abstract class Account {
    
    private String accountNumber;
    private double currentBalance;
    private Customer dude;
    
    ArrayList<Address> homes = new ArrayList<Address>();
    
    public Account(String a, Customer b) {
        accountNumber = a;
        dude = b;
        currentBalance = 0;
        //perhaps initialize the arraylist?
    }
    
    public abstract void updateBalance();
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public double getCurrentBalance() {
        return currentBalance;
    }
    
    public void setBalance(double b) {
        currentBalance = b;
    }
    
    public Customer getCustomer() {
        return dude;
    }
    
    public Address[] getAddresses() {
        Address [] a = new Address[homes.size()];
        a = homes.toArray(a);
        return a;
    }
    
    public boolean addAddress(Address a) {
        if (a != null) {
            return homes.add(a);
        } else {
            return false;
        }
    }
    
    public boolean removeAddress(Address a) {
        return homes.remove(a);
    }
    
}
