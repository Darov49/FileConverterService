package org.example.service.structure;

/**
 * Класс для хранения характеристик ноутбука в JSON
 */
public class LaptopJSON {
    private int id;
    private String model;
    private CPU cpu;
    private int ram;
    private int storage;
    private GPU gpu;

    public LaptopJSON(int id, String model, CPU cpu, int ram, int storage, GPU gpu) {
        this.id = id;
        this.model = model;
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
        this.gpu = gpu;
    }

    public LaptopJSON() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public GPU getGpu() {
        return gpu;
    }

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }
}
