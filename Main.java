/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc241hw07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

//import com.csc241hw05.xml.holidays;
/**
 *
 * @author Cesar
 */
public class Main {

    static private MyHandler handler;
    static private ArrayList<Account> loadedAccounts;
    static private ArrayList<Customer> loadedCustomers;
    static private ArrayList<Address> loadedAddresses;
    static private ArrayList<Meter> loadedMeters;
    static private ArrayList<MeterReading> loadedReadings;

    /**
     * @param args the command line arguments
     */
    public static boolean loadFile(File xml) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(xml, handler);
            loadedAccounts.addAll(handler.getAccountList());
            loadedCustomers.addAll(handler.getCustomerList());
            loadedAddresses.addAll(handler.getAddressList());
            loadedMeters.addAll(handler.getMeterList());
            loadedReadings.addAll(handler.getReadingList());
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public static void clear() {
        loadedAccounts.clear();
        loadedCustomers.clear();
        loadedAddresses.clear();
        loadedMeters.clear();
        loadedReadings.clear();
        handler.getAccountList().clear();
        handler.getCustomerList().clear();
        handler.getAddressList().clear();
        handler.getMeterList().clear();
        handler.getReadingList().clear();
    }

    public static void show(String[] array) {
        // TODO fix no records found bug

        if (array[1].equalsIgnoreCase("customer")) {
            boolean found = false;
            for (Customer c : loadedCustomers) {
                if (c.getLastName().equalsIgnoreCase(array[2])) {
                    found = true;
                    System.out.println("Last name: " + c.getLastName());
                    System.out.println("First name: " + c.getFirstName());
                    System.out.println("Accounts:");
                    for (Account a : c.getAccounts()) {
                        System.out.println("  " + a.getAccountNumber());
                    }
                }
            }
            if (!found) {
                System.out.println("No records found");
            }
        } else if (array[1].equalsIgnoreCase("account")) {
            boolean found = false;
            DecimalFormat df = new DecimalFormat("#.##");
            for (Account a : loadedAccounts) {
                if (a.getAccountNumber().equalsIgnoreCase(array[2])) {
                    found = true;
                    System.out.println("Account number: " + a.getAccountNumber());
                    System.out.println("Balance: $" + df.format(a.getCurrentBalance()));
                    System.out.println("Addresses:");
                    for (Address ad : a.getAddresses()) {
                        System.out.println("  " + ad.getNumber() + " " + ad.getStreet());
                    }
                }
            }
            if (!found) {
                System.out.println("No records found");
            }
        } else if (array[1].equalsIgnoreCase("address")) {
            boolean found = false;
            String street = new String();
            for (int i = 2; i < array.length; i++) {
                String z = array[i];
                street += z + " ";
            }
            street = street.trim();
            for (Address ad : loadedAddresses) {
                if (ad.getBoth().equalsIgnoreCase(street)) {
                    found = true;
                    System.out.println("Number: " + ad.getNumber());
                    System.out.println("Street: " + ad.getStreet());
                    System.out.println("Zip: " + ad.getZipCode());
                    System.out.println("Type: " + ad.getType());
                    if (ad instanceof Apartment) {
                        System.out.println("Unit: " + ((Apartment) ad).getUnit());
                    }
                    System.out.println("Meters:");
                    for (Meter m : ad.getMeters()) {
                        System.out.println("  " + m.getID());
                    }
                }
            }
            if (!found) {
                System.out.println("No records found");
            }

        } else if (array[1].equalsIgnoreCase("meter")) {
            boolean found = false;
            for (Meter m : loadedMeters) {
                if (m.getID().equalsIgnoreCase(array[2])) {
                    found = true;
                    System.out.println("ID: " + m.getID());
                    System.out.println("Brand: " + m.getBrand());
                    System.out.println("Location: " + m.getLocation());
                    System.out.println("Type: " + m.getType());
                    System.out.println("Meters Readings:");
                    for (MeterReading mr : m.getReadings()) {
                        System.out.println("  " + mr.getDateTime().minusHours(5).format(DateTimeFormatter.ISO_DATE) + " " + mr.getDateTime().minusHours(5).format(DateTimeFormatter.ISO_TIME));
                    }
                }
            }
            if (!found) {
                System.out.println("No records found");
            }
        }
    }

    public static void check(String[] array) {
        // TODO implement error statements
        if (array[1].equalsIgnoreCase("customer")) {
            boolean found = false;
            for (Customer c : loadedCustomers) {
                if (c.getAccounts().length == 0) {
                    found = true;
                    System.out.println(c.getLastName() + ", " + c.getFirstName());
                }
            }
            if (!found) {
                System.out.println("No records found");
            }
        } else if (array[1].equalsIgnoreCase("account")) {
            boolean found = false;
            for (Account a : loadedAccounts) {
                if (a.getAddresses().length == 0) {
                    found = true;
                    System.out.println(a.getAccountNumber());
                }
            }
            if (!found) {
                System.out.println("No records found");
            }
        } else if (array[1].equalsIgnoreCase("address")) {
            boolean found = false;
            for (Address ad : loadedAddresses) {
                if (ad.getMeters().length == 0) {
                    found = true;
                    System.out.println(ad.getStreet());
                }
            }
            if (!found) {
                System.out.println("No records found");
            }
        } else if (array[1].equalsIgnoreCase("meter")) {
            boolean found = false;
            for (Meter m : loadedMeters) {
                if (m.getReadings().length == 0) {
                    found = true;
                    System.out.println(m.getID());
                }
                for (MeterReading mr : m.getReadings()) {
                    if (!mr.getFlag().equalsIgnoreCase(m.getType())) {
                        System.out.println(m.getID());
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                System.out.println("No records found");
            }
        }
    }

    public static void reportBalance() {
        DecimalFormat df = new DecimalFormat("#.##");
        for (Account a : loadedAccounts) {
            System.out.println("Account: " + a.getAccountNumber());
            System.out.println("  Customer: " + a.getCustomer().getLastName() + ", " + a.getCustomer().getFirstName());
            System.out.println("  Balance: $" + df.format(a.getCurrentBalance()));
        }
        if (loadedAccounts.size() == 0) {
            System.out.println("No records found");
        }
    }

    public static void main(String[] args) {

        handler = new MyHandler();
        loadedAccounts = new ArrayList<>();
        loadedCustomers = new ArrayList<>();
        loadedAddresses = new ArrayList<>();
        loadedMeters = new ArrayList<>();
        loadedReadings = new ArrayList<>();

        System.out.println("Welcome to the Energy Management System");

        Scanner kb = new Scanner(System.in);
        // File xml = new File(kb.nextLine());
        // loadFile(xml);
        boolean doneRunning = false;
        String userInput = " ";

        while (!doneRunning) {
            System.out.println("Enter a command:");
            userInput = kb.nextLine();
            String parts[] = userInput.split(" ");

            if (userInput.equalsIgnoreCase("quit")) {
                doneRunning = true;
            } else if (parts[0].equalsIgnoreCase("load")) {
//                if (userInput.contains(".xml")){
                File xml = new File(parts[1]);
                if (loadFile(xml)) {
                    System.out.println("Load successful: " + parts[1]);
                } else {
                    System.out.println("Invalid input file - " + parts[1]);
                }
            } else if (parts[0].equalsIgnoreCase("show")) {
                show(parts);
            } else if (parts[0].equalsIgnoreCase("clear")) {
                clear();
            } else if (parts[0].equalsIgnoreCase("check")) {
                check(parts);
            } else if (parts[0].equalsIgnoreCase("report") && parts[1].equalsIgnoreCase("balance")) {
                reportBalance();
            } else {
                System.out.println("Invalid command: " + parts[0]);
            }
        }
        System.out.println("Program ending");
    }

}
