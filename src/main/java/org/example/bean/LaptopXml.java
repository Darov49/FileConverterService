package org.example.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

/**
 * Класс для хранения характеристик ноутбука в XML
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopXml {
    @JacksonXmlProperty(isAttribute = true)
    private int id;
    private String brand;
    private String model;
    private Cpu cpu;
    private int ram;
    private int storage;
    private Gpu gpu;
}
