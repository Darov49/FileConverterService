package org.example.service.structure;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

/**
 * Класс для хранения информации о бренде в JSON
 */
@JsonTypeName("brand")
public class BrandJSON {
    private String name;
    private List<LaptopJSON> laptops;

    public BrandJSON(String name, List<LaptopJSON> laptops) {
        this.name = name;
        this.laptops = laptops;
    }

    public BrandJSON() {}

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
