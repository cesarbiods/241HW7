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
public class Customer {
    
    private String lastName;
    private String firstName;
    private Address address01; 
   //make arraylist first, just 
    ArrayList<Account> accounts = new ArrayList<Account>();
    
    public Customer(String l, String f, Address a) {
        lastName = l;
        firstName = f;
        address01 = a;
    }
    
    public Customer(String l, String f) {
        lastName = l;
        firstName = f;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public Address getMailingAddress(){
        return address01;   
    }
    
    public void setMailingAdddress(Address a) {
        address01 = a;
    }
    
    public Account[] getAccounts() {
        Account [] a = new Account[accounts.size()];
        a = accounts.toArray(a);
        return a;
    }
    
    public boolean addAccount(Account c) {
        if (c != null) {
        return accounts.add(c);
    } else {
          return false;
      }
    }
    
    public boolean removeAccount(Account c) {
        return accounts.remove(c);
    }
    
}
