/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc241hw07;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//import com.journaldev.xml.Employee;
/**
 *
 * @author Cesar
 */
public class MyHandler extends DefaultHandler {

    private ArrayList<Account> accountList;
    private ArrayList<Customer> customerList;
    private ArrayList<Address> addressList;
    private ArrayList<Meter> meterList;
    private ArrayList<MeterReading> readingList;
    private Account currentAccount;
    private Customer currentCustomer;
    private Address currentAddress;
    private Meter currentMeter;
    private MeterReading currentReading;
    
    public MyHandler() {
        accountList = new ArrayList<>();
        customerList = new ArrayList<>();
        addressList = new ArrayList<>();
        meterList = new ArrayList<>();
        readingList = new ArrayList<>();
    }

    //getter method for employee list
    public ArrayList<Account> getAccountList() {
        return accountList;
    }
    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }
    public ArrayList<Address> getAddressList() {
        return addressList;
    }
    public ArrayList<Meter> getMeterList() {
        return meterList;
    }
    public ArrayList<MeterReading> getReadingList() {
        return readingList;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase("Account")) {
            //create a new Account and put it in Map
            if (attributes.getValue("type").equalsIgnoreCase("residential")) {
                currentAccount = new ResidentialAccount(attributes.getValue("accountNumber"), currentCustomer);
            } else if (attributes.getValue("type").equalsIgnoreCase("commercial")) {
                currentAccount = new CommercialAccount(attributes.getValue("accountNumber"), currentCustomer);
            }
            if (currentCustomer != null) {
                currentCustomer.addAccount(currentAccount);
            }

        } else if (qName.equalsIgnoreCase("customer")) {
            currentCustomer = new Customer(attributes.getValue("lastName"), attributes.getValue("firstName"));
            customerList.add(currentCustomer);

        } else if (qName.equalsIgnoreCase("address") && attributes.getValue("type").equalsIgnoreCase("mailing")) {
            if (currentAccount instanceof ResidentialAccount) {
                if (attributes.getValue("unit").equals(null)) {
                    currentAddress = new House(attributes.getValue("street"), Integer.parseInt(attributes.getValue("number")), attributes.getValue("zipCode"), attributes.getValue("type"));
                } else {
                    currentAddress = new Apartment(attributes.getValue("street"), Integer.parseInt(attributes.getValue("number")), attributes.getValue("zipCode"), attributes.getValue("type"), attributes.getValue("unit"));
                }
            } else {
                currentAddress = new Commercial(attributes.getValue("street"), Integer.parseInt(attributes.getValue("number")), attributes.getValue("zipCode"), attributes.getValue("type"));
            }
            if (currentCustomer != null) {
                currentCustomer.setMailingAdddress(currentAddress);
            }
        } else if (qName.equalsIgnoreCase("address")) {
            if (attributes.getValue("type").equals("apartment")) {
                currentAddress = new Apartment(attributes.getValue("street"), Integer.parseInt(attributes.getValue("number")), attributes.getValue("zipCode"), attributes.getValue("type"), attributes.getValue("unit"));

            } else if (attributes.getValue("type").equals("house")) {
                currentAddress = new House(attributes.getValue("street"), Integer.parseInt(attributes.getValue("number")), attributes.getValue("zipCode"), attributes.getValue("type"));

            } else if (attributes.getValue("type").equals("commercial")) {
                currentAddress = new Commercial(attributes.getValue("street"), Integer.parseInt(attributes.getValue("number")), attributes.getValue("zipCode"), attributes.getValue("type"));

            }
            currentAccount.addAddress(currentAddress);
            addressList.add(currentAddress);
            
        } else if (qName.equalsIgnoreCase("meter")) {

            if (attributes.getValue("type").equalsIgnoreCase("push")) {

                currentMeter = new PushMeter(attributes.getValue("id"), attributes.getValue("brand"), attributes.getValue("type"));
                

                //currentAccount.updateBalance();
            } else {

                currentMeter = new PollMeter(attributes.getValue("id"), attributes.getValue("brand"), attributes.getValue("type"));
              

            }
            currentMeter.setLocation(currentAddress, attributes.getValue("location"));
            currentAddress.addMeter(currentMeter);
            meterList.add(currentMeter);

        } else if (qName.equalsIgnoreCase("meterreading")) {
            // Five hours from GMT
            ZoneOffset z = ZoneOffset.ofHours(5);
            // get number of seconds
            long epoch = Long.parseLong(attributes.getValue("date"));
            // create object
            LocalDateTime d = LocalDateTime.ofEpochSecond(epoch, 0, z);
            currentMeter.addReading(new MeterReading(Double.parseDouble(attributes.getValue("reading")), d, attributes.getValue("flag"), currentMeter));
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Customer")) {
            currentCustomer = null;
        } else if (qName.equalsIgnoreCase("Account")) {
            //initialize list
            currentAccount.updateBalance();
            
            //add account object to list
            accountList.add(currentAccount);
            currentAccount = null;
        } else if (qName.equalsIgnoreCase("address")) {
            currentAddress = null;
        } else if (qName.equalsIgnoreCase("meter")) {
            currentMeter = null;
        } 
    }

}
