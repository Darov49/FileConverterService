package org.example.service;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.example.exception.ConverterException;
import org.example.factory.ConverterFactory;

import static org.example.extension.FileExtensions.JSON_EXTENSION;
import static org.example.extension.FileExtensions.XML_EXTENSION;

/**
 * Класс для обработки конвертации файлов
 */
@UtilityClass
public class ConversionService {

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

            val converter = ConverterFactory.createConverter(conversionType);
            converter.convert(inputFile, outputFile);
        } catch (Exception converterException) {
            throw new ConverterException("Конвертация прервана: " + converterException.getMessage() + "\n", converterException);
        }
    }
}
