package org.example.service.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.example.bean.BrandJson;
import org.example.bean.LaptopXml;
import org.example.exception.ConverterException;
import org.example.bean.BrandsJson;
import org.example.bean.LaptopsXml;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/***
 * Класс для конвертации файла из JSON в XML
 */
@Log4j2
@UtilityClass
public class JsonToXmlConverter {
    public void convert(final String inputFilePath, final String outputFilePath,
                        final XmlMapper xmlMapper, final ObjectMapper objectMapper) throws ConverterException {
        val brands = readJSON(inputFilePath, objectMapper);
        val laptops = transform((brands));
        write(laptops, outputFilePath, xmlMapper);
    }

    private List<BrandJson> readJSON(final String inputFile, final ObjectMapper objectMapper) throws ConverterException {
        try {
            return objectMapper.readValue(new File(inputFile), BrandsJson.class).getBrands();
        } catch (IOException fileReadException) {
            log.error("Ошибка при считывании файла json", fileReadException);
            throw new ConverterException("не удалось считать файл json");
        }
    }

    private LaptopsXml transform(final List<BrandJson> brands) throws ConverterException {
        try {
            return LaptopsXml.builder().laptops(brands.stream()
                    .flatMap(brand -> brand.getLaptops().stream()
                            .map(laptopJSON -> LaptopXml.builder()
                                    .id(laptopJSON.getId())
                                    .brand(brand.getName())
                                    .model(laptopJSON.getModel())
                                    .cpu(laptopJSON.getCpu())
                                    .ram(laptopJSON.getRam())
                                    .storage(laptopJSON.getStorage())
                                    .gpu(laptopJSON.getGpu())
                                    .build()))
                    .sorted(Comparator.comparingInt(LaptopXml::getId))
                    .toList()).build();
        } catch (Exception fileConvertException) {
            log.error("Ошибка при конвертировании файла из json в xml", fileConvertException);
            throw new ConverterException("не удалось сконвертировать файл");
        }
    }

    private void write(final LaptopsXml laptopsXML, final String outputFile, final XmlMapper xmlMapper)
            throws ConverterException {
        try {
            xmlMapper.writeValue(new File(outputFile), laptopsXML);
        } catch (Exception fileWriteException) {
            log.error("Ошибка при записи в файл xml", fileWriteException);
            throw new ConverterException("не удалось записать данные в файл xml");
        }
    }
}
