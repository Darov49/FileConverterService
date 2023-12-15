package org.example.service;

import lombok.experimental.UtilityClass;
import org.example.exception.ConverterException;

import java.io.File;

@UtilityClass
public class FileValidator {
    private static final String XML_EXTENSION = ".xml";
    private static final String JSON_EXTENSION = ".json";
    private File checkingFile;

    public void validateFiles(final String inputFile, final String outputFile) throws ConverterException {
        if (!isCorrectInputFile(inputFile)) {
            throw new ConverterException("Некорректный входной файл");
        }

        if (!isCorrectOutputFile(inputFile, outputFile)) {
            throw new ConverterException("Некорректный выходной файл");
        }
    }
    private boolean isCorrectInputFile(final String inputFile) {
        checkingFile = new File(inputFile);
        return (checkingFile.exists() && !checkingFile.isDirectory() &&
                (inputFile.endsWith(XML_EXTENSION) || inputFile.endsWith(JSON_EXTENSION)));
    }

    private boolean isCorrectOutputFile(final String inputFile, final String outputFile) {
        checkingFile = new File(outputFile);
        final boolean isCorrectExtension = (inputFile.endsWith(XML_EXTENSION) && outputFile.endsWith(JSON_EXTENSION) ||
                inputFile.endsWith(JSON_EXTENSION) && outputFile.endsWith(XML_EXTENSION));
        return (!checkingFile.isDirectory() && checkingFile.getParentFile().exists() && isCorrectExtension);
    }
}
