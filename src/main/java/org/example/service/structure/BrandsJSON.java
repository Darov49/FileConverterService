package org.example.service.structure;

import java.util.List;

/**
 * Класс для хранения списка брендов в JSON
 */
public class BrandsJSON {
    private List<BrandJSON> brands;

    public List<BrandJSON> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandJSON> brands) {
        this.brands = brands;
    }

}
