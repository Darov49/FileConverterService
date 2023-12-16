package org.example.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Laptop {
    @JacksonXmlProperty(isAttribute = true)
    private int id;
    private String model;
    private Cpu cpu;
    private int ram;
    private int storage;
    private Gpu gpu;
}
