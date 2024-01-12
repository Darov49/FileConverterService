package ru.vyatsu.fileconverter.bean;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

/**
 * Класс для хранения списка брендов в JSON
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandsJson {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonProperty("brands")
    private List<BrandJson> brands;
}
