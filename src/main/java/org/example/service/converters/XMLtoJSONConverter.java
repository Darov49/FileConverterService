package org.example.service.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.example.service.ConverterException;
import org.example.service.structure.BrandJSON;
import org.example.service.structure.BrandsJSON;
import org.example.service.structure.LaptopJSON;
import org.example.service.structure.LaptopXML;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

/***
 * Класс для конвертации файла из XML в JSON
 */
@UtilityClass
@Log4j2
public class XMLtoJSONConverter {
    public void convert(final String inputFilePath, final String outputFilePath,
                        final XmlMapper xmlMapper, final ObjectMapper objectMapper) throws ConverterException {
        val laptops = readXML(inputFilePath, xmlMapper);
        val brands = transform((laptops));
        write(brands, outputFilePath, objectMapper);
    }

    private List<LaptopXML> readXML(final String inputFile, final XmlMapper xmlMapper) throws ConverterException {
        try {
            return xmlMapper.readValue(new File(inputFile), new TypeReference<>() {
            });
        } catch (Exception fileReadException) {
            log.error("Не удалось считать файл xml\n");
            throw new ConverterException(fileReadException);
        }
    }

    private BrandsJSON transform(final List<LaptopXML> laptops) throws ConverterException {
        try {
            return BrandsJSON.builder().brands(laptops.stream()
                    .collect(Collectors.groupingBy(LaptopXML::getBrand,
                            Collectors.mapping(laptopXML -> LaptopJSON.builder()
                                    .id(laptopXML.getId())
                                    .model(laptopXML.getModel())
                                    .cpu(laptopXML.getCpu())
                                    .ram(laptopXML.getRam())
                                    .storage(laptopXML.getStorage())
                                    .gpu(laptopXML.getGpu())
                                    .build(), Collectors.toList())))
                    .entrySet().stream()
                    .map(entry -> BrandJSON.builder()
                            .name(entry.getKey())
                            .laptops(entry.getValue())
                            .build())
                    .sorted(Comparator.comparing(BrandJSON::getName))
                    .toList()).build();
        } catch (Exception fileConvertException) {
            log.error("Не удалось сконвертировать файл\n");
            throw new ConverterException(fileConvertException);
        }
    }

    private void write(final BrandsJSON brandsJSON, final String outputFile, final ObjectMapper objectMapper)
            throws ConverterException {
        try {
            objectMapper.writeValue(new File(outputFile), brandsJSON);
        } catch (Exception fileWriteException) {
            log.error("Не удалось записать данные в файл json\n");
            throw new ConverterException(fileWriteException);
        }
    }
}

