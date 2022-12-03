package com.example.app_viewmodel;

public class Bmi {
    private double height;
    private double weight;
    private double bmi;

    public Bmi() {

    }

    public Bmi(double height, double weight) {
        this.height = height;
        this.weight = weight;
        bmi = weight / Math.pow(height/100, 2);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    @Override
    public String toString() {
        return "Bmi{" +
                "height=" + height +
                ", weight=" + weight +
                ", bmi=" + bmi +
                '}';
    }
}
