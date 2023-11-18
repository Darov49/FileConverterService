package org.example.service.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.val;
import org.example.service.ConverterException;
import org.example.service.structure.BrandJSON;
import org.example.service.structure.BrandsJSON;
import org.example.service.structure.LaptopJSON;
import org.example.service.structure.LaptopXML;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

/***
 * Класс для конвертации файла из XML в JSON
 */
public class XMLtoJSONConverter {
    private XMLtoJSONConverter() throws ConverterException {
        throw new ConverterException("Нельзя использовать конструктор для статического класса");
    }

    public static void convert(String inputFilePath, String outputFilePath) throws ConverterException {
        List<LaptopXML> laptops = readXML(inputFilePath);
        BrandsJSON brands = transform((laptops));
        write(brands, outputFilePath);
    }

    private static List<LaptopXML> readXML(String pathName) throws ConverterException {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            File file = new File(pathName);
            return xmlMapper.readValue(file, new TypeReference<>() {});
        } catch (Exception e) {
            throw new ConverterException("Не удалось считать файл xml");
        }

    }

    private static BrandsJSON transform(List<LaptopXML> laptops) throws ConverterException {
        try {
            List<BrandJSON> brands = new ArrayList<>();

            for (LaptopXML laptopXML : laptops) {
                val laptopJSON = LaptopJSON.builder()
                        .id(laptopXML.getId())
                        .model(laptopXML.getModel())
                        .cpu(laptopXML.getCpu())
                        .ram(laptopXML.getRam())
                        .storage(laptopXML.getStorage())
                        .gpu(laptopXML.getGpu())
                        .build();

                String brandName = laptopXML.getBrand();
                int i;
                for (i = 0; i < brands.size(); i++) {
                    BrandJSON brand = brands.get(i);
                    if (brand.getName().equals(brandName)) {
                        brand.getLaptops().addLast(laptopJSON);
                        break;
                    }
                }
                if (i == brands.size()) {
                    brands.addLast(BrandJSON.builder()
                            .name(brandName)
                            .laptops(new ArrayList<>(Collections.singletonList(laptopJSON)))
                            .build());
                }
            }

            return BrandsJSON.builder().brands(brands).build();
        } catch (Exception e) {
            throw new ConverterException("Не удалось сконвертировать файл. Проверьте входной файл xml");
        }
    }

    private static void write(BrandsJSON brandsJSON, String outputFile) throws ConverterException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(outputFile), brandsJSON);
        } catch (Exception e) {
            throw new ConverterException("Не удалось записать данные в файл json");
        }
    }
}

