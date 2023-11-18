package org.example.service.structure;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

/**
 * Класс для хранения списка брендов в JSON
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandsJSON {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonProperty("brands")
    private List<BrandJSON> brands;
}
