package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.example.bean.BrandJson;
import org.example.bean.BrandsJson;
import org.example.bean.LaptopXml;
import org.example.bean.LaptopsXml;
import org.example.exception.ConverterException;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Log4j2
@Data
@AllArgsConstructor
public class FileWorker {

    private final String inputFilePath;
    private final String outputFilePath;

    private static final XmlMapper xmlMapper = (XmlMapper) new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);


    public List<LaptopXml> readXML() throws ConverterException {
        try {
            return xmlMapper.readValue(new File(inputFilePath), new TypeReference<>() {
            });
        } catch (IOException fileReadException) {
            log.error("Ошибка при считывании файла xml", fileReadException);
            throw new ConverterException("не удалось считать файл xml");
        }
    }

    public List<BrandJson> readJSON() throws ConverterException {
        try {
            return objectMapper.readValue(new File(inputFilePath), BrandsJson.class).getBrands();
        } catch (IOException fileReadException) {
            log.error("Ошибка при считывании файла json", fileReadException);
            throw new ConverterException("не удалось считать файл json");
        }
    }

    public void writeJson(final BrandsJson brandsJSON) throws ConverterException {
        try {
            objectMapper.writeValue(new File(outputFilePath), brandsJSON);
        } catch (Exception fileWriteException) {
            log.error("Ошибка при записи в файл json", fileWriteException);
            throw new ConverterException("не удалось записать данные в файл json");
        }
    }

    public void writeXml(final LaptopsXml laptopsXML)
            throws ConverterException {
        try {
            xmlMapper.writeValue(new File(outputFilePath), laptopsXML);
        } catch (Exception fileWriteException) {
            log.error("Ошибка при записи в файл xml", fileWriteException);
            throw new ConverterException("не удалось записать данные в файл xml");
        }
    }
}
