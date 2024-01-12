package ru.vyatsu.fileconverter.service;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import ru.vyatsu.fileconverter.exception.ConverterException;
import ru.vyatsu.fileconverter.factory.ConverterFactory;
import ru.vyatsu.fileconverter.extension.FileExtensions;

/**
 * Класс для обработки конвертации файлов
 */
@UtilityClass
public class ConversionService {

    private ConversionType getConversionType(final String inputFile, final String outputFile) {
        if (StringUtils.endsWith(inputFile, FileExtensions.XML_EXTENSION) && StringUtils.endsWith(outputFile, FileExtensions.JSON_EXTENSION)) {
            return ConversionType.XML_TO_JSON;
        }
        return ConversionType.JSON_TO_XML;
    }

    public void convert(final String inputFile, final String outputFile) throws ConverterException {
        try {
            FileValidator.validateFiles(inputFile, outputFile);

            val converter = ConverterFactory.createConverter(getConversionType(inputFile, outputFile));
            converter.convert(inputFile, outputFile);
        } catch (Exception converterException) {
            throw new ConverterException("Конвертация прервана: " + converterException.getMessage(), converterException);
        }
    }
}
