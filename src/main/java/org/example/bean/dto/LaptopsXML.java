package org.example.bean.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;

/**
 * Класс для хранения списка ноутбуков в файле XML
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JacksonXmlRootElement(localName = "laptops")
public class LaptopsXML {
    @JsonProperty("laptop")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<LaptopXML> laptops;
}
