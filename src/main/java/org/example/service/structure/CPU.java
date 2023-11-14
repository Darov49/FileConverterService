package org.example.service.structure;

/**
 * Класс для хранения информации о процессоре ноутбука
 */
public class CPU {
    private String model;
    private int numCores;
    private double frequency;

    public CPU(String model, int numCores, double frequency) {
        this.model = model;
        this.numCores = numCores;
        this.frequency = frequency;
    }

    public CPU() {}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getNumCores() {
        return numCores;
    }

    public void setNumCores(int numCores) {
        this.numCores = numCores;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
}
