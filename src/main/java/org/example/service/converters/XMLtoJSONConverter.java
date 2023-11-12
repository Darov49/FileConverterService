package org.example.service.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.service.structure.BrandJSON;
import org.example.service.structure.BrandsJSON;
import org.example.service.structure.LaptopJSON;
import org.example.service.structure.LaptopXML;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

/***
 * Класс для конвертации файла из XML в JSON
 */
public class XMLtoJSONConverter {

    public static void convert(String inputFilePath, String outputFilePath) throws Exception {
        List<LaptopXML> laptops = readXML(inputFilePath);
        BrandsJSON brands = transform((laptops));
        write(brands, outputFilePath);
    }

    private static List<LaptopXML> readXML(String pathName) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        File file = new File(pathName);
        return xmlMapper.readValue(file, new TypeReference<List<LaptopXML>>() {});
    }

    private static BrandsJSON transform(List<LaptopXML> laptops) {
        List<BrandJSON> brands = new ArrayList<>();
        for (LaptopXML laptop : laptops) {
            LaptopJSON laptopJSON = new LaptopJSON();
            laptopJSON.setId(laptop.getId());
            laptopJSON.setModel(laptop.getModel());
            laptopJSON.setCpu(laptop.getCpu());
            laptopJSON.setRam(laptop.getRam());
            laptopJSON.setStorage(laptop.getStorage());
            laptopJSON.setGpu(laptop.getGpu());

            String brandName = laptop.getBrand();
            int i;
            for (i = 0; i < brands.size(); i++) {
                if (brands.get(i).getName().equals(brandName)) {
                    brands.get(i).getLaptops().addLast(laptopJSON);
                    break;
                }
            }
            if (i == brands.size()) {
                BrandJSON newBrand = new BrandJSON();
                newBrand.setName(brandName);
                newBrand.setLaptops(new ArrayList<>());
                newBrand.getLaptops().addLast(laptopJSON);
                brands.addLast(newBrand);
            }

        }

        BrandsJSON brandsJSON = new BrandsJSON();
        brandsJSON.setBrands(brands);
        return brandsJSON;
    }

    private static void write(BrandsJSON brandsJSON, String outputFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(new File(outputFile), brandsJSON);
        }
        catch (Exception e){}
    }

}
