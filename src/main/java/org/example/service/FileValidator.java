package org.example.service;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

import static org.example.extension.FileExtensions.JSON_EXTENSION;
import static org.example.extension.FileExtensions.XML_EXTENSION;

@UtilityClass
public class FileValidator {
    private File checkingFile;

    public void validateFiles(final String inputFile, final String outputFile) throws IOException {
        if (!isCorrectInputFile(inputFile)) {
            throw new IOException("некорректный входной файл " + inputFile);
        }

        if (!isCorrectOutputFile(inputFile, outputFile)) {
            throw new IOException("некорректный выходной файл " + outputFile);
        }
    }
    private boolean isCorrectInputFile(final String inputFile) {
        checkingFile = new File(inputFile);
        return (checkingFile.exists() && !checkingFile.isDirectory() &&
                (StringUtils.endsWith(inputFile, XML_EXTENSION) || StringUtils.endsWith(inputFile, JSON_EXTENSION)));
    }

    private boolean isCorrectOutputFile(final String inputFile, final String outputFile) {
        checkingFile = new File(outputFile);
        final boolean isCorrectExtension =
                (StringUtils.endsWith(inputFile, XML_EXTENSION) && StringUtils.endsWith(outputFile, JSON_EXTENSION) ||
                        StringUtils.endsWith(inputFile, JSON_EXTENSION) && StringUtils.endsWith(outputFile, XML_EXTENSION));
        return (!checkingFile.isDirectory() && checkingFile.getParentFile().exists() && isCorrectExtension);
    }
}
