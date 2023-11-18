package org.example.service.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;
import org.example.service.ConverterException;
import org.example.service.structure.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/***
 * Класс для конвертации файла из JSON в XML
 */
@UtilityClass
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
       } catch (Exception e) {
           throw new ConverterException("Не удалось считать файл json");
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
        } catch (Exception e) {
            throw new ConverterException("Не удалось сконвертировать файл. Проверьте входной файл json");
        }
    }

    public void write(LaptopsXML laptopsXML, String outputFile, XmlMapper xmlMapper) throws ConverterException {
        try {
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT).writeValue(new File(outputFile), laptopsXML);
        } catch (Exception e) {
            throw new ConverterException("Не удалось записать данные в файл xml");
        }
    }
}
