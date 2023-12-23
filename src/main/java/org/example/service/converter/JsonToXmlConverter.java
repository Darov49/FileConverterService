package org.example.service.converter;

import lombok.AllArgsConstructor;
import org.example.bean.BrandJson;
import org.example.bean.LaptopXml;
import org.example.bean.LaptopsXml;
import org.example.exception.ConverterException;
import org.example.mapper.LaptopMapper;

import java.util.Comparator;
import java.util.List;

/***
 * Класс для конвертации файла из JSON в XML
 */
@AllArgsConstructor
public class JsonToXmlConverter implements Converter {
    private final List<BrandJson> brands;

    @Override
    public LaptopsXml convert() throws ConverterException {
        try {
            return LaptopsXml.builder().laptops(brands.stream()
                    .flatMap(brand -> brand.getLaptops().stream()
                            .map(laptopJSON -> LaptopMapper.INSTANCE.toLaptopXml(laptopJSON, brand)))
                    .sorted(Comparator.comparingInt(LaptopXml::getId))
                    .toList()).build();
        } catch (Exception fileConvertException) {
            throw new ConverterException("Ошибка при конвертировании файла из json в xml", fileConvertException);
        }
    }
}
