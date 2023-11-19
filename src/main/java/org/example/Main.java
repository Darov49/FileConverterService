package org.example;

import org.example.service.ConverterException;
import org.example.service.MenuService;
import org.example.service.converters.Conversion;

public class Main {
    public static void main(String[] args) throws ConverterException {
        try {
            switch (args.length) {
                // интерактивный выбор аргументов
                case 0 -> {
                    final String inputFile = MenuService.getInputFile();
                    if (!MenuService.isCorrectInputFile(inputFile)) {
                        throw new ConverterException("Некорректный входной файл");
                    }
                    final String outputFile = MenuService.getOutputFile();
                    if (!MenuService.isCorrectOutputFile(inputFile, outputFile)) {
                        throw new ConverterException("Некорректный выходной файл");
                    }
                    Conversion.convert(inputFile, outputFile);
                }
                // аргументы из консоли
                case 2 -> {
                    try {
                        Conversion.convert(args[0], args[1]);
                    } catch (ConverterException e) {
                        throw new ConverterException("Завершение программы");
                    }
                }
                default -> System.err.println("Неверное число аргументов");
            }
            System.out.println("Конвертация завершена");
        } catch (ConverterException e) {
            throw new ConverterException("Программа прервана");
        }


    }
}