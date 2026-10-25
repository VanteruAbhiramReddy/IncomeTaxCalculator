package com.model;

import java.util.*;

public class TaxSlab {
    private Hashtable<String, Double> oldSlabs = new Hashtable<String, Double>();
    private Hashtable<String, Double> newSlabs = new Hashtable<String, Double>();

    public TaxSlab() {
        oldSlabs.put("0", 0.00);
        oldSlabs.put("250000", 0.05);
        oldSlabs.put("500000", 0.20);
        oldSlabs.put("1000000", 0.30);

        newSlabs.put("0", 0.00);
        newSlabs.put("400000", 0.05);
        newSlabs.put("800000", 0.10);
        newSlabs.put("1200000", 0.15);
        newSlabs.put("1600000", 0.20);
        newSlabs.put("2000000", 0.25);
        newSlabs.put("2400000", 0.30);
    }

    public void enumerateSlabs(String regime) { 
        Hashtable<String, Double> slabs =
                regime.equalsIgnoreCase("old") ? oldSlabs : newSlabs;
        ArrayList<Integer> limits = new ArrayList<Integer>();
        Enumeration<String> keys = slabs.keys();

        while (keys.hasMoreElements()) {
            limits.add(Integer.parseInt(keys.nextElement()));
        }

        Collections.sort(limits);

        System.out.println("\n" + regime.toUpperCase() + " REGIME SLABS (demo):");
        for (int limit : limits) {
            System.out.println("From Rs. " + limit + " : "
                    + (slabs.get(String.valueOf(limit)) * 100) + "%");
        }
    }
}
