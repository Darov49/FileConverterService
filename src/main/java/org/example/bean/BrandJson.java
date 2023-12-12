package org.example.bean;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.util.List;

/**
 * Класс для хранения информации о бренде в JSON
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonTypeName("brand")
public class BrandJson {
    private String name;
    private List<LaptopJson> laptops;
}
