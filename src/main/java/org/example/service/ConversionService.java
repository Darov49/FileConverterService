package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.example.exception.ConverterException;
import org.example.service.converter.JsonToXmlConverter;
import org.example.service.converter.XmlToJsonConverter;

/**
 * Класс для обработки конвертации файлов
 */
@UtilityClass
public class ConversionService {

    private final XmlMapper xmlMapper = (XmlMapper) new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private static final String XML_EXTENSION = ".xml";
    private static final String JSON_EXTENSION = ".json";


    private ConversionType getConversionType(final String inputFile, final String outputFile) {
        if (StringUtils.endsWith(inputFile, XML_EXTENSION) && StringUtils.endsWith(outputFile, JSON_EXTENSION)) {
            return ConversionType.XML_TO_JSON;
        }
        return ConversionType.JSON_TO_XML;
    }

    public void convert(final String inputFile, final String outputFile) throws ConverterException {
        try {
            val conversionType = getConversionType(inputFile, outputFile);
            switch (conversionType) {
                case XML_TO_JSON -> XmlToJsonConverter.convert(inputFile, outputFile, xmlMapper, objectMapper);
                case JSON_TO_XML -> JsonToXmlConverter.convert(inputFile, outputFile, xmlMapper, objectMapper);
            }
        } catch (ConverterException converterException) {
            System.err.println("Конвертация прервана: " + converterException.getMessage() +
                    "\nСмотри подробности в файле логов");
            throw converterException;
        }
    }
}
