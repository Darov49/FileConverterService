package ru.vyatsu.fileconverter.service;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import ru.vyatsu.fileconverter.extension.FileExtensions;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class FileValidator {
    public void validateFiles(final String inputFile, final String outputFile) throws IOException {
        if (!isCorrectInputFile(inputFile)) {
            throw new IOException("некорректный входной файл " + inputFile);
        }

        if (!isCorrectOutputFile(inputFile, outputFile)) {
            throw new IOException("некорректный выходной файл " + outputFile);
        }
    }
    private boolean isCorrectInputFile(final String inputFile) {
        val checkingFile = new File(inputFile);
        return (checkingFile.exists() && !checkingFile.isDirectory() &&
                (StringUtils.endsWith(inputFile, FileExtensions.XML_EXTENSION) || StringUtils.endsWith(inputFile, FileExtensions.JSON_EXTENSION)));
    }

    private boolean isCorrectOutputFile(final String inputFile, final String outputFile) {
        val checkingFile = new File(outputFile);
        final boolean isCorrectExtension =
                (StringUtils.endsWith(inputFile, FileExtensions.XML_EXTENSION) && StringUtils.endsWith(outputFile, FileExtensions.JSON_EXTENSION) ||
                        StringUtils.endsWith(inputFile, FileExtensions.JSON_EXTENSION) && StringUtils.endsWith(outputFile, FileExtensions.XML_EXTENSION));
        return (!checkingFile.isDirectory() && checkingFile.getParentFile().exists() && isCorrectExtension);
    }
}
