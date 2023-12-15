package org.example.service.converter;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@AllArgsConstructor
public class JsonToXmlConverter {
    private final List<BrandJson> brands;

    public LaptopsXml convert() throws ConverterException {
        try {
            return LaptopsXml.builder().laptops(brands.stream()
                    .flatMap(brand -> brand.getLaptops().stream()
                            .map(laptopJSON -> LaptopMapper.INSTANCE.toLaptopXml(laptopJSON, brand)))
                    .sorted(Comparator.comparingInt(LaptopXml::getId))
                    .toList()).build();
        } catch (Exception fileConvertException) {
            log.error("Ошибка при конвертировании файла из json в xml", fileConvertException);
            throw new ConverterException("не удалось сконвертировать файл");
        }
    }
}
