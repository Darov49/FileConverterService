package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.bean.BrandJson;
import org.example.bean.BrandsJson;
import org.example.bean.LaptopXml;
import org.example.bean.LaptopsXml;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@AllArgsConstructor
public class FileWorker {

    private final String inputFilePath;
    private final String outputFilePath;

    private static final XmlMapper xmlMapper = (XmlMapper) new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);


    public List<LaptopXml> readXML() throws IOException {
        try {
            return xmlMapper.readValue(new File(inputFilePath), new TypeReference<>() {
            });
        } catch (IOException fileReadException) {
            throw new IOException("ошибка при считывании файла xml - " + inputFilePath, fileReadException);
        }
    }

    public List<BrandJson> readJSON() throws IOException {
        try {
            return objectMapper.readValue(new File(inputFilePath), BrandsJson.class).getBrands();
        } catch (IOException fileReadException) {
            throw new IOException("ошибка при считывании файла json - " + inputFilePath, fileReadException);
        }
    }

    public void writeJson(final BrandsJson brandsJSON) throws IOException {
        try {
            objectMapper.writeValue(new File(outputFilePath), brandsJSON);
        } catch (Exception fileWriteException) {
            throw new IOException("ошибка при записи в файл json - " + outputFilePath, fileWriteException);
        }
    }

    public void writeXml(final LaptopsXml laptopsXML) throws IOException {
        try {
            xmlMapper.writeValue(new File(outputFilePath), laptopsXML);
        } catch (Exception fileWriteException) {
            throw new IOException("ошибка при записи в файл xml - " + outputFilePath, fileWriteException);
        }
    }
}
