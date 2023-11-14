package org.example.service.structure;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Класс для хранения списка ноутбуков в файле XML
 */
@JacksonXmlRootElement(localName = "laptops")
public class LaptopsXML {
    @JsonProperty("laptop")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<LaptopXML> laptops;

    public LaptopsXML (List<LaptopXML> laptops) {
        this.laptops = laptops;
    }

    public LaptopsXML() {}

    public List<LaptopXML> getLaptops() {
        return laptops;
    }

    public void setLaptops(List<LaptopXML> laptops) {
        this.laptops = laptops;
    }

}
