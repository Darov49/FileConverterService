package org.example.service.converters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.example.service.ConverterException;

@UtilityClass
public class Conversion {

    final XmlMapper xmlMapper = new XmlMapper();
    final ObjectMapper objectMapper = new ObjectMapper();

    private enum conversionType {
        XML_TO_JSON, JSON_TO_XML, INVALID
    }

    private conversionType getConversionType(String inputFile, String outputFile) {
        if (inputFile.endsWith(".xml") && outputFile.endsWith(".json")) {
            return conversionType.XML_TO_JSON;
        }

        if (inputFile.endsWith(".json") && outputFile.endsWith(".xml")) {
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
                default -> System.err.println("Некорректный ввод");
            }
        } catch (ConverterException converterException) {
            throw new ConverterException("Завершение программы");
        }
    }

}
