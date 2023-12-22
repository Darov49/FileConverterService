package org.example.service;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Scanner;

@UtilityClass
public class MenuService {
    private static final Scanner SCANNER = new Scanner(System.in);

    public Pair<String, String> getFileNames() {
        System.out.println("Путь к файлу для конвертации:");
        val inputFileName = SCANNER.nextLine();

        System.out.println("Файл для сохранения:");
        val outputFileName = SCANNER.nextLine();
        return Pair.of(inputFileName, outputFileName);
    }
}
