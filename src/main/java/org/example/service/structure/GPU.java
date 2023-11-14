package org.example.service.structure;

/**
 * Класс для хранения информации о видеокарте ноутбука
 */
public class GPU {
    private String model;
    private int volume;

    public GPU(String model, int volume) {
        this.model = model;
        this.volume = volume;
    }

    public GPU() {}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
