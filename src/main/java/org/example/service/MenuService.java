package org.example.service;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.Scanner;

@UtilityClass
public class MenuService {
    private static final Scanner scanner = new Scanner(System.in);

    public String[] getFileNames() {
        System.out.println("Путь к файлу для конвертации:");
        val inputFileName = scanner.nextLine();

        System.out.println("Файл для сохранения:");
        val outputFileName = scanner.nextLine();
        return new String[]{inputFileName, outputFileName};
    }
}
