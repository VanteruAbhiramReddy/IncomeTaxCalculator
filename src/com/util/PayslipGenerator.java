package com.util;

public class PayslipGenerator {
    public static String formatCurrency(double amount) {
        return String.format("Rs. %,.2f", amount);
    }

    public static void printSummary(String type, double receipts, double taxableIncome,
                                    double oldTax, double newTax, String lowerRegime) {
        System.out.println("\n========== TAX SUMMARY ==========");
        System.out.println("Income type          : " + type);
        System.out.println("Gross income/receipts: " + formatCurrency(receipts));
        System.out.println("Estimated taxable income: " + formatCurrency(taxableIncome));
        System.out.println("Estimated old-regime tax: " + formatCurrency(oldTax));
        System.out.println("Estimated new-regime tax: " + formatCurrency(newTax));
        System.out.println("Lower estimate       : " + lowerRegime.toUpperCase()
                + " regime (" + formatCurrency(Math.min(oldTax, newTax)) + ")");
        System.out.println("=================================");
    }
}
