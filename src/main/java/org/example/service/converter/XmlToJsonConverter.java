package org.example.service.converter;

import lombok.AllArgsConstructor;
import org.example.bean.BrandJson;
import org.example.bean.BrandsJson;
import org.example.bean.LaptopXml;
import org.example.exception.ConverterException;
import org.example.mapper.LaptopMapper;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Класс для конвертации файла из XML в JSON
 */
@AllArgsConstructor
public class XmlToJsonConverter implements Converter {

    private final List<LaptopXml> laptops;

    @Override
    public BrandsJson convert() throws ConverterException {
        try {
            return BrandsJson.builder().brands(laptops.stream()
                    .collect(Collectors.groupingBy(LaptopXml::getBrand,
                            Collectors.mapping(LaptopMapper.INSTANCE::toLaptopJson, Collectors.toList())))
                    .entrySet().stream()
                    .map(entry -> BrandJson.builder()
                            .name(entry.getKey())
                            .laptops(entry.getValue())
                            .build())
                    .sorted(Comparator.comparing(BrandJson::getName))
                    .toList()).build();
        } catch (Exception fileConvertException) {
            throw new ConverterException("Ошибка при конвертировании файла из xml в json", fileConvertException);
        }
    }
}

