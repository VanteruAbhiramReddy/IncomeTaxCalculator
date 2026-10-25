import java.util.Scanner;

import com.model.TaxSlab;
import com.util.PayslipGenerator;

public class Main {
    public static double calculateTax(double originalIncome, String regime) {
        double income = originalIncome;
        double tax = 0;

        if (regime.equalsIgnoreCase("old")) {
            if (income > 1000000) {
                tax += (income - 1000000) * 0.30;
                income = 1000000;
            }
            if (income > 500000) {
                tax += (income - 500000) * 0.20;
                income = 500000;
            }
            if (income > 250000) {
                tax += (income - 250000) * 0.05;
            }
            if (originalIncome <= 500000)
                tax = 0; // simplified rebate
        } else {
            if (income > 2400000) {
                tax += (income - 2400000) * 0.30;
                income = 2400000;
            }
            if (income > 2000000) {
                tax += (income - 2000000) * 0.25;
                income = 2000000;
            }
            if (income > 1600000) {
                tax += (income - 1600000) * 0.20;
                income = 1600000;
            }
            if (income > 1200000) {
                tax += (income - 1200000) * 0.15;
                income = 1200000;
            }
            if (income > 800000) {
                tax += (income - 800000) * 0.10;
                income = 800000;
            }
            if (income > 400000) {
                tax += (income - 400000) * 0.05;
            }
            if (originalIncome <= 1200000)
                tax = 0; // simplified rebate
        }

        return Math.max(0, tax) * 1.04; // 4% cess in this example
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("BEGINNER INCOME TAX ESTIMATOR");
        System.out.println("1. Salary / job");
        System.out.println("2. Eligible business (Section 44AD)");
        System.out.println("3. Specified profession (Section 44ADA)");
        System.out.print("Choose income type: ");
        int choice = input.nextInt();

        System.out.print("Enter annual salary or gross receipts (Rs.): ");
        double amount = input.nextDouble();

        if (amount < 0) {
            System.out.println("Income cannot be negative.");
            input.close();
            return;
        }

        double taxableIncome = amount;
        String type;

        if (choice == 1) {
            type = "Salary / job";
            System.out.println("Sections 44AD and 44ADA do not apply to salary income.");
        } else if (choice == 2) {
            type = "Business under Section 44AD";
            System.out.print("Enter cash receipts included in total (Rs.): ");
            double cash = input.nextDouble();

            if (cash < 0 || cash > amount) {
                System.out.println("Invalid cash receipts amount.");
                input.close();
                return;
            }

            double limit = (cash <= amount * 0.05) ? 30000000 : 20000000;
            if (amount > limit) {
                System.out.println("Receipts exceed this demo's 44AD limit.");
                input.close();
                return;
            }

            taxableIncome = (amount - cash) * 0.06 + cash * 0.08;
            System.out.println("Estimated presumptive income under 44AD: "
                    + PayslipGenerator.formatCurrency(taxableIncome));
        } else if (choice == 3) {
            type = "Specified profession under Section 44ADA";
            System.out.print("Enter cash receipts included in total (Rs.): ");
            double cash = input.nextDouble();

            if (cash < 0 || cash > amount) {
                System.out.println("Invalid cash receipts amount.");
                input.close();
                return;
            }

            double limit = (cash <= amount * 0.05) ? 7500000 : 5000000;
            if (amount > limit) {
                System.out.println("Receipts exceed this demo's 44ADA limit.");
                input.close();
                return;
            }

            taxableIncome = amount * 0.50;
            System.out.println("Estimated presumptive income under 44ADA: "
                    + PayslipGenerator.formatCurrency(taxableIncome));
        } else {
            System.out.println("Invalid choice.");
            input.close();
            return;
        }

        double oldTax = calculateTax(taxableIncome, "old");
        double newTax = calculateTax(taxableIncome, "new");
        String lowerRegime = oldTax <= newTax ? "old" : "new";

        PayslipGenerator.printSummary(type, amount, taxableIncome,
                oldTax, newTax, lowerRegime);

        TaxSlab slabs = new TaxSlab();
        slabs.enumerateSlabs(lowerRegime);

        String sample = "PAYROLL";
        System.out.println("\nFirst three letters: " + sample.substring(0, 3));

        input.close();
    }
}
