package org.example.bean.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.util.List;

/**
 * Класс для хранения информации о бренде в JSON
 */
@JsonTypeName("brand")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandJSON {
    private String name;
    private List<LaptopJSON> laptops;
}
