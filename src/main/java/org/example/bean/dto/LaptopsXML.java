package org.example.bean.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.List;

/**
 * Класс для хранения списка ноутбуков в файле XML
 */
@JacksonXmlRootElement(localName = "laptops")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopsXML {
    @JsonProperty("laptop")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<LaptopXML> laptops;
}
