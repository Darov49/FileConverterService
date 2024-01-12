package ru.vyatsu.fileconverter.bean;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

/**
 * Класс для хранения характеристик ноутбука в XML
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "brand", "model", "cpu", "ram", "storage", "gpu"})
public class LaptopXml extends Laptop {

    private String brand;

    @Builder
    public LaptopXml (int id, String brand, String model, Cpu cpu, int ram, int storage, Gpu gpu) {
        super(id, model, cpu, ram, storage, gpu);
        this.brand = brand;
    }
}
