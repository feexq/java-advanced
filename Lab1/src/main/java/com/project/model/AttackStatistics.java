package com.project.model;

/**
 * Class representing attack statistics.
 */
public class AttackStatistics {

    private int min;               // Minimum attack power
    private int max;               // Maximum attack power
    private double average;        // Average attack power
    private double standardDeviation;   // Standard deviation of attack power

    // Constructor
    public AttackStatistics(int min, int max, double standardDeviation, double average) {
        this.min = min;
        this.standardDeviation = standardDeviation;
        this.average = average;
        this.max = max;
    }

    // Getters and Setters
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "AttackStatistics{" +
                "min=" + min +
                ", max=" + max +
                ", average=" + average +
                ", standardDeviation=" + standardDeviation +
                '}';
    }

}
