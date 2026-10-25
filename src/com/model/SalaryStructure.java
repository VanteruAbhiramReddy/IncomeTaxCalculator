package com.model;

public class SalaryStructure {
    public double basic, hra, da, deductions;

    public SalaryStructure(double basic, double hra, double da, double deductions) {
        this.basic = basic;
        this.hra = hra;
        this.da = da;
        this.deductions = deductions;
    }

    public static double calculateGross(double basic, double hra, double da) {
        return basic + hra + da;
    }
}
