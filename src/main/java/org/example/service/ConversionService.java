package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.example.exceptions.ConverterException;
import org.example.service.converters.JSONtoXMLConverter;
import org.example.service.converters.XMLtoJSONConverter;

/**
 * Класс для обработки конвертации файлов
 */
@UtilityClass
@Log4j2
public class ConversionService {

    private final XmlMapper xmlMapper = (XmlMapper) new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private final String xmlExtension = ".xml";
    private final String jsonExtension = ".json";

    private enum conversionType {
        XML_TO_JSON, JSON_TO_XML, INVALID
    }

    private conversionType getConversionType(String inputFile, String outputFile) {
        if (inputFile.endsWith(xmlExtension) && outputFile.endsWith(jsonExtension)) {
            return conversionType.XML_TO_JSON;
        }

        if (inputFile.endsWith(jsonExtension) && outputFile.endsWith(xmlExtension)) {
            return conversionType.JSON_TO_XML;
        }

        return conversionType.INVALID;
    }

    public void convert(String inputFile, String outputFile) throws ConverterException {
        try {
            val conversionType = getConversionType(inputFile, outputFile);
            switch (conversionType) {
                case XML_TO_JSON -> XMLtoJSONConverter.convert(inputFile, outputFile, xmlMapper, objectMapper);
                case JSON_TO_XML -> JSONtoXMLConverter.convert(inputFile, outputFile, xmlMapper, objectMapper);
                default -> log.error("Некорректный ввод");
            }
        } catch (ConverterException converterException) {
            log.error("Конвертация прервана\n");
            throw converterException;
        }
    }
}
