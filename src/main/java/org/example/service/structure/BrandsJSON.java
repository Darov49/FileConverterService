package org.example.service.structure;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Класс для хранения списка брендов в JSON
 */
public class BrandsJSON {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonProperty("brands")
    private List<BrandJSON> brands;

    public List<BrandJSON> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandJSON> brands) {
        this.brands = brands;
    }

}
