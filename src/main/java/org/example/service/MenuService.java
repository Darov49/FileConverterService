package org.example.service;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.Scanner;

@UtilityClass
public class MenuService {
    private final Scanner scanner = new Scanner(System.in);
    private final String jsonExtension = ".json";
    private final String xmlExtension = ".xml";
    private File f;

    public String getInputFile() {
        System.out.println("Путь к файлу для конвертации: ");
        return scanner.nextLine();
    }

    public String getOutputFile() {
        System.out.println("Файл для сохранения: ");
        return scanner.nextLine();
    }


    public boolean isCorrectInputFile(String inputFile) {
        f = new File(inputFile);
        return (f.exists() && !f.isDirectory() &&
                (inputFile.endsWith(xmlExtension) || inputFile.endsWith(jsonExtension)));
    }

    public boolean isCorrectOutputFile(String inputFile, String outputFile) {
        f = new File(outputFile);
        final boolean isCorrectExtension = (inputFile.endsWith(xmlExtension) && inputFile.endsWith(jsonExtension) ||
                outputFile.endsWith(jsonExtension) && inputFile.endsWith(xmlExtension));
        return (!f.isDirectory() && isCorrectExtension);
    }
}
