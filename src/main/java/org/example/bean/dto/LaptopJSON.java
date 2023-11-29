package org.example.bean.dto;

import lombok.*;

/**
 * Класс для хранения характеристик ноутбука в JSON
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopJSON {
    private int id;
    private String model;
    private CPU cpu;
    private int ram;
    private int storage;
    private GPU gpu;
}
