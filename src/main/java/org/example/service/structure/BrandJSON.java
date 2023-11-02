package org.example.service.structure;

import java.util.List;

/**
 * Класс для хранения информации о бренде в JSON
 */
public class BrandJSON {
    private String name;
    private List<LaptopJSON> laptops;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LaptopJSON> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<LaptopJSON> laptops) {
        this.laptops = laptops;
    }
}
