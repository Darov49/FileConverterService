package org.example.service;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.example.exceptions.ConverterException;

import java.io.File;
import java.util.Scanner;

@UtilityClass
public class MenuService {
    private final Scanner scanner = new Scanner(System.in);
    private final String jsonExtension = ".json";
    private final String xmlExtension = ".xml";
    private File checkingFile;


    public String[] getFileNames() throws ConverterException {
        val inputFileName = getInputFile();
        if (!isCorrectInputFile(inputFileName)) {
            System.err.println("Некорректный входной файл");
            throw new ConverterException();
        }

        val outputFileName = getOutputFile();
        if (!isCorrectOutputFile(inputFileName, outputFileName)) {
            System.err.println("Некорректный выходной файл");
            throw new ConverterException();
        }
        return new String[] {inputFileName, outputFileName} ;
    }

    private String getInputFile() {
        System.out.println("Путь к файлу для конвертации:");
        return scanner.nextLine();
    }

    private String getOutputFile() {
        System.out.println("Файл для сохранения:");
        return scanner.nextLine();
    }


    private boolean isCorrectInputFile(final String inputFile) {
        checkingFile = new File(inputFile);
        return (checkingFile.exists() && !checkingFile.isDirectory() &&
                (inputFile.endsWith(xmlExtension) || inputFile.endsWith(jsonExtension)));
    }

    private boolean isCorrectOutputFile(final String inputFile, String outputFile) {
        checkingFile = new File(outputFile);
        final boolean isCorrectExtension = (inputFile.endsWith(xmlExtension) && outputFile.endsWith(jsonExtension) ||
                inputFile.endsWith(jsonExtension) && outputFile.endsWith(xmlExtension));
        return (!checkingFile.isDirectory() && checkingFile.getParentFile().exists() && isCorrectExtension);
    }
}
