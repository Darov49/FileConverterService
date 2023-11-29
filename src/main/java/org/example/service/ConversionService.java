package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.example.exceptions.ConverterException;
import org.example.service.converters.JSONtoXMLConverter;
import org.example.service.converters.XMLtoJSONConverter;

/**
 * Класс для обработки конвертации файлов
 */
@UtilityClass
public class ConversionService {

    private final XmlMapper xmlMapper = (XmlMapper) new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private final String xmlExtension = ".xml";
    private final String jsonExtension = ".json";

    private enum ConversionType {
        XML_TO_JSON, JSON_TO_XML
    }

    private ConversionType getConversionType(String inputFile, String outputFile) {
        if (inputFile.endsWith(xmlExtension) && outputFile.endsWith(jsonExtension)) {
            return ConversionType.XML_TO_JSON;
        }
        return ConversionType.JSON_TO_XML;
    }

    public void convert(String inputFile, String outputFile) throws ConverterException {
        try {
            val conversionType = getConversionType(inputFile, outputFile);
            switch (conversionType) {
                case XML_TO_JSON -> XMLtoJSONConverter.convert(inputFile, outputFile, xmlMapper, objectMapper);
                case JSON_TO_XML -> JSONtoXMLConverter.convert(inputFile, outputFile, xmlMapper, objectMapper);
                default -> System.err.println("Некорректный ввод");
            }
        } catch (ConverterException converterException) {
            System.err.println("Конвертация прервана");
            throw converterException;
        }
    }
}
