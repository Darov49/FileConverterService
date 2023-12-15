package org.example.service.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.example.bean.BrandJson;
import org.example.bean.BrandsJson;
import org.example.bean.LaptopXml;
import org.example.exception.ConverterException;
import org.example.bean.LaptopJson;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mapper.LaptopMapper;

/***
 * Класс для конвертации файла из XML в JSON
 */
@Log4j2
@UtilityClass
public class XmlToJsonConverter {
    public void convert(final String inputFilePath, final String outputFilePath,
                        final XmlMapper xmlMapper, final ObjectMapper objectMapper) throws ConverterException {
        val laptops = readXML(inputFilePath, xmlMapper);
        val brands = transform((laptops));
        write(brands, outputFilePath, objectMapper);
    }

    public List<LaptopXml> readXML(final String inputFile, final XmlMapper xmlMapper) throws ConverterException {
        try {
            return xmlMapper.readValue(new File(inputFile), new TypeReference<>() {
            });
        } catch (IOException fileReadException) {
            log.error("Ошибка при считывании файла xml", fileReadException);
            throw new ConverterException("не удалось считать файл xml");
        }
    }

    private BrandsJson transform(final List<LaptopXml> laptops) throws ConverterException {
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

    private void write(final BrandsJson brandsJSON, final String outputFile, final ObjectMapper objectMapper)
            throws ConverterException {
        try {
            objectMapper.writeValue(new File(outputFile), brandsJSON);
        } catch (Exception fileWriteException) {
            log.error("Ошибка при записи в файл json", fileWriteException);
            throw new ConverterException("не удалось записать данные в файл json");
        }
    }
}

