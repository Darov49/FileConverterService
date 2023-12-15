package org.example.service;

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
            FileValidator.validateFiles(inputFile, outputFile);
            val conversionType = getConversionType(inputFile, outputFile);
            FileWorker fileWorker = new FileWorker(inputFile, outputFile);
            switch (conversionType) {
                case XML_TO_JSON -> {
                    val laptops = fileWorker.readXML();

                    XmlToJsonConverter converter = new XmlToJsonConverter(laptops);
                    val brands = converter.convert();

                    fileWorker.writeJson(brands);
                }
                case JSON_TO_XML -> {
                    val brands = fileWorker.readJSON();

                    JsonToXmlConverter converter = new JsonToXmlConverter(brands);
                    val laptops = converter.convert();

                    fileWorker.writeXml(laptops);
                }
            }
        } catch (ConverterException converterException) {
            System.err.println("Конвертация прервана: " + converterException.getMessage() +
                    "\nСмотри подробности в файле логов");
            throw converterException;
        }
    }
}
