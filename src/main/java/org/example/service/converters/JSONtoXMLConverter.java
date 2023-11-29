package org.example.service.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.example.exceptions.ConverterException;
import org.example.bean.dto.BrandJSON;
import org.example.bean.dto.BrandsJSON;
import org.example.bean.dto.LaptopXML;
import org.example.bean.dto.LaptopsXML;

import java.io.File;
import java.util.Comparator;
import java.util.List;

/***
 * Класс для конвертации файла из JSON в XML
 */
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
        } catch (Exception fileReadException) {
            System.err.println("Не удалось считать файл json");
            throw new ConverterException(fileReadException);
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
            System.err.println("Не удалось сконвертировать файл");
            throw new ConverterException(fileConvertException);
        }
    }

    private void write(final LaptopsXML laptopsXML, final String outputFile, final XmlMapper xmlMapper)
            throws ConverterException {
        try {
            xmlMapper.writeValue(new File(outputFile), laptopsXML);
        } catch (Exception fileWriteException) {
            System.err.println("Не удалось записать данные в файл xml");
            throw new ConverterException(fileWriteException);
        }
    }
}
