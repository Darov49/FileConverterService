package org.example.bean;

import lombok.*;

/**
 * Класс для хранения характеристик ноутбука в JSON
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopJson {
    private int id;
    private String model;
    private Cpu cpu;
    private int ram;
    private int storage;
    private Gpu gpu;
}
