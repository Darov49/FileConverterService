package org.example.bean.dto;

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
public class BrandJSON {
    private String name;
    private List<LaptopJSON> laptops;
}
