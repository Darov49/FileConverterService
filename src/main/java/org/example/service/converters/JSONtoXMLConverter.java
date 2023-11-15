package org.example.service.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.service.ConverterException;
import org.example.service.structure.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/***
 * Класс для конвертации файла из JSON в XML
 */
public class JSONtoXMLConverter {
    private JSONtoXMLConverter() throws ConverterException {
        throw new ConverterException("Нельзя использовать конструктор для статического класса");
    }

    public static void convert(String inputFilePath, String outputFilePath) throws ConverterException {
        List<BrandJSON> brands = readJSON(inputFilePath);
        LaptopsXML laptops = transform((brands));
        write(laptops, outputFilePath);
    }

    public static List<BrandJSON> readJSON(String pathName) throws ConverterException {
       try {
           ObjectMapper objectMapper = new ObjectMapper();
           File file = new File(pathName);
           BrandsJSON brands = objectMapper.readValue(file, new TypeReference<>() {
           });
           return brands.getBrands();
       } catch (Exception e) {
           throw new ConverterException("Не удалось считать файл json");
       }
    }

    public static LaptopsXML transform(List<BrandJSON> brands) throws ConverterException {
        try {
            List<LaptopXML> laptops = new ArrayList<>();
            for (BrandJSON brand : brands) {
                for (LaptopJSON laptopJSON : brand.getLaptops()) {
                    LaptopXML laptopXML = new LaptopXML(laptopJSON.getId(), brand.getName(), laptopJSON.getModel(),
                            laptopJSON.getCpu(), laptopJSON.getRam(), laptopJSON.getStorage(), laptopJSON.getGpu());
                    laptops.add(laptopXML);
                }
            }

            LaptopsXML laptopsXML = new LaptopsXML();
            laptopsXML.setLaptops(laptops);
            return laptopsXML;
        } catch (Exception e) {
            throw new ConverterException("Не удалось сконвертировать файл. Проверьте входной файл json");
        }
    }

    public static void write(LaptopsXML laptopsXML, String outputFile) throws  ConverterException{
        try {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(new File(outputFile), laptopsXML);
        } catch (Exception e) {
            throw new ConverterException("Не удалось записать данные в файл xml");
        }
    }
}
