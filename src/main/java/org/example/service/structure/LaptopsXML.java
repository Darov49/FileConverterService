package org.example.service.structure;


import java.util.List;

/**
 * Класс для хранения списка ноутбуков в файле XML
 */
public class LaptopsXML {
    private List<LaptopXML> laptops;

    public List<LaptopXML> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<LaptopXML> laptops) {
        this.laptops = laptops;
    }

}
