package org.example;

import lombok.val;
import org.example.exceptions.ConverterException;
import org.example.service.MenuService;
import org.example.service.ConversionService;

public class Main {
    public static void main(String[] args) {
        try {
            switch (args.length) {
                // интерактивный выбор аргументов
                case 0 -> {
                    val fileNames = MenuService.getFileNames(); // получение имен входного и выходного файлов
                    ConversionService.convert(fileNames[0], fileNames[1]);
                }
                // аргументы из консоли
                case 2 -> ConversionService.convert(args[0], args[1]);
                default -> {
                    System.err.println("Неверное число аргументов");
                    return;
                }
            }
            System.out.println("Конвертация завершена успешно");
        } catch (ConverterException exception) {
            System.err.println("Завершение программы");
        }
    }
}
