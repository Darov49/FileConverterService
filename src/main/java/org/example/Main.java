package org.example;

import lombok.val;
import org.example.exception.ConverterException;
import org.example.exception.ExceptionHandler;
import org.example.service.MenuService;
import org.example.service.ConversionService;

import static java.lang.System.exit;

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
                default -> throw new IllegalArgumentException(String.format("Неверное число аргументов: %d%n", args.length));
            }
            System.out.println("Конвертация завершена успешно");
        } catch (Exception exception) {
            ExceptionHandler.handleException(exception);
        }
    }
}
