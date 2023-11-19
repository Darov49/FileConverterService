package org.example.service.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.example.service.ConverterException;
import org.example.service.structure.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/***
 * Класс для конвертации файла из JSON в XML
 */
@UtilityClass
@Log4j2
public class JSONtoXMLConverter {
    public void convert(String inputFilePath, String outputFilePath, XmlMapper xmlMapper, ObjectMapper objectMapper)
            throws ConverterException {
        List<BrandJSON> brands = readJSON(inputFilePath, objectMapper);
        LaptopsXML laptops = transform((brands));
        write(laptops, outputFilePath, xmlMapper);
    }

    public List<BrandJSON> readJSON(String inputFile, ObjectMapper objectMapper) throws ConverterException {
       try {
           BrandsJSON brands = objectMapper.readValue(new File(inputFile), new TypeReference<>() {});
           return brands.getBrands();
       } catch (Exception fileReadException) {
           log.error("Не удалось считать файл json\n");
           throw new ConverterException(fileReadException);
       }
    }

    public LaptopsXML transform(List<BrandJSON> brands) throws ConverterException {
        try {
            List<LaptopXML> laptops = new ArrayList<>();

            for (BrandJSON brand : brands) {
                for (LaptopJSON laptopJSON : brand.getLaptops()) {
                    laptops.add(LaptopXML.builder()
                            .id(laptopJSON.getId())
                            .brand(brand.getName())
                            .model(laptopJSON.getModel())
                            .cpu(laptopJSON.getCpu())
                            .ram(laptopJSON.getRam())
                            .storage(laptopJSON.getStorage())
                            .gpu(laptopJSON.getGpu())
                            .build());
                }
            }

            return LaptopsXML.builder().laptops(laptops).build();
        } catch (Exception fileConvertException) {
            log.error("Не удалось сконвертировать файл\n");
            throw new ConverterException(fileConvertException);
        }
    }

    public void write(LaptopsXML laptopsXML, String outputFile, XmlMapper xmlMapper) throws ConverterException {
        try {
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT).writeValue(new File(outputFile), laptopsXML);
        } catch (Exception fileWriteException) {
            log.error("Не удалось записать данные в файл xml\n");
            throw new ConverterException(fileWriteException);
        }
    }
}
