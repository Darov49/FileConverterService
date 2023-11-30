package org.example.service.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.example.exceptions.ConverterException;
import org.example.bean.dto.BrandJSON;
import org.example.bean.dto.BrandsJSON;
import org.example.bean.dto.LaptopXML;
import org.example.bean.dto.LaptopsXML;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/***
 * Класс для конвертации файла из JSON в XML
 */
@Log4j2
@UtilityClass
public class JSONtoXMLConverter {
    public void convert(final String inputFilePath, final String outputFilePath,
                        final XmlMapper xmlMapper, final ObjectMapper objectMapper) throws ConverterException {
        val brands = readJSON(inputFilePath, objectMapper);
        val laptops = transform((brands));
        write(laptops, outputFilePath, xmlMapper);
    }

    private List<BrandJSON> readJSON(final String inputFile, final ObjectMapper objectMapper) throws ConverterException {
        try {
            final BrandsJSON brands = objectMapper.readValue(new File(inputFile), new TypeReference<>() {
            });
            return brands.getBrands();
        } catch (IOException fileReadException) {
            log.error("Ошибка при считывании файла json", fileReadException);
            throw new ConverterException("не удалось считать файл json");
        }
    }

    private LaptopsXML transform(final List<BrandJSON> brands) throws ConverterException {
        try {
            return LaptopsXML.builder().laptops(brands.stream()
                    .flatMap(brand -> brand.getLaptops().stream()
                            .map(laptopJSON -> LaptopXML.builder()
                                    .id(laptopJSON.getId())
                                    .brand(brand.getName())
                                    .model(laptopJSON.getModel())
                                    .cpu(laptopJSON.getCpu())
                                    .ram(laptopJSON.getRam())
                                    .storage(laptopJSON.getStorage())
                                    .gpu(laptopJSON.getGpu())
                                    .build()))
                    .sorted(Comparator.comparingInt(LaptopXML::getId))
                    .toList()).build();
        } catch (Exception fileConvertException) {
            log.error("Ошибка при конвертировании файла из json в xml", fileConvertException);
            throw new ConverterException("не удалось сконвертировать файл");
        }
    }

    private void write(final LaptopsXML laptopsXML, final String outputFile, final XmlMapper xmlMapper)
            throws ConverterException {
        try {
            xmlMapper.writeValue(new File(outputFile), laptopsXML);
        } catch (Exception fileWriteException) {
            log.error("Ошибка при записи в файл xml", fileWriteException);
            throw new ConverterException("не удалось записать данные в файл xml");
        }
    }
}
