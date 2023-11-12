package org.example.service.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.example.service.structure.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/***
 * Класс для конвертации файла из JSON в XML
 */
public class JSONtoXMLConverter {

    public static void convert(String inputFilePath, String outputFilePath) throws Exception {
        List<BrandJSON> brands = readJSON(inputFilePath);
        LaptopsXML laptops = transform((brands));
        write(laptops, outputFilePath);
    }

    public static List<BrandJSON> readJSON(String pathName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(pathName);
        BrandsJSON brands = objectMapper.readValue(file, new TypeReference<BrandsJSON>() {});
        return brands.getBrands();
    }

    public static LaptopsXML transform(List<BrandJSON> brands) {
        List<LaptopXML> laptops = new ArrayList<>();
        for (BrandJSON brand : brands) {
            for (LaptopJSON laptopJSON : brand.getLaptops()) {
                LaptopXML laptopXML = new LaptopXML();
                laptopXML.setId(laptopJSON.getId());
                laptopXML.setBrand(brand.getName());
                laptopXML.setModel(laptopJSON.getModel());
                laptopXML.setCpu(laptopJSON.getCpu());
                laptopXML.setRam(laptopJSON.getRam());
                laptopXML.setStorage(laptopJSON.getStorage());
                laptopXML.setGpu(laptopJSON.getGpu());

                laptops.add(laptopXML);
            }
        }

        laptops.sort(Comparator.comparingInt(LaptopXML::getId)); // ноутбуки в списке упорядочены по их id

        LaptopsXML laptopsXML = new LaptopsXML();
        laptopsXML.setLaptops(laptops);
        return laptopsXML;
    }

    public static void write(LaptopsXML laptopsXML, String outputFile) {
        XmlMapper xmlMapper = new XmlMapper();

        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            xmlMapper.writeValue(new File(outputFile), laptopsXML);
        } catch (Exception e) { }
    }
}
