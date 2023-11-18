package org.example.service.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.experimental.UtilityClass;
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
@UtilityClass
public class XMLtoJSONConverter {
    public void convert(String inputFilePath, String outputFilePath, XmlMapper xmlMapper, ObjectMapper objectMapper)
            throws ConverterException {
        List<LaptopXML> laptops = readXML(inputFilePath, xmlMapper);
        BrandsJSON brands = transform((laptops));
        write(brands, outputFilePath, objectMapper);
    }

    private List<LaptopXML> readXML(String inputFile, XmlMapper xmlMapper) throws ConverterException {
        try {
            return xmlMapper.readValue(new File(inputFile), new TypeReference<>() {});
        } catch (Exception e) {
            throw new ConverterException("Не удалось считать файл xml");
        }
    }

    private BrandsJSON transform(List<LaptopXML> laptops) throws ConverterException {
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

    private void write(BrandsJSON brandsJSON, String outputFile, ObjectMapper objectMapper) throws ConverterException {
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT).writeValue(new File(outputFile), brandsJSON);
        } catch (Exception e) {
            throw new ConverterException("Не удалось записать данные в файл json");
        }
    }
}

