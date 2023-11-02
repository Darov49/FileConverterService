package org.example.service.structure;

/**
 * Класс для хранения характеристик ноутбука в XML
 */
public class LaptopXML extends LaptopJSON{
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
