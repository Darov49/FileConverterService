package org.example.bean.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

/**
 * Класс для хранения характеристик ноутбука в XML
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopXML {
    @JacksonXmlProperty(isAttribute = true)
    private int id;
    private String brand;
    private String model;
    private CPU cpu;
    private int ram;
    private int storage;
    private GPU gpu;
}
