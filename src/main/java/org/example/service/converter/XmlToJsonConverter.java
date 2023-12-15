package org.example.service.converter;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@AllArgsConstructor
public class XmlToJsonConverter {

    private final List<LaptopXml> laptops;

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
            log.error("Ошибка при конвертировании файла из xml в json", fileConvertException);
            throw new ConverterException("не удалось сконвертировать файл", fileConvertException);
        }
    }
}
