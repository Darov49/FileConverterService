package org.example;

import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.example.service.ConverterException;
import org.example.service.MenuService;
import org.example.service.converters.Conversion;

@Log4j2
public class Main {
    public static void main(String[] args) {
        try {
            switch (args.length) {
                // интерактивный выбор аргументов
                case 0 -> {
                    val inputFile = MenuService.getInputFile();
                    if (!MenuService.isCorrectInputFile(inputFile)) {
                        log.error("Некорректный входной файл");
                        return;
                    }
                    val outputFile = MenuService.getOutputFile();
                    if (!MenuService.isCorrectOutputFile(inputFile, outputFile)) {
                        log.error("Некорректный выходной файл");
                        return;
                    }
                    Conversion.convert(inputFile, outputFile);
                }
                // аргументы из консоли
                case 2 -> Conversion.convert(args[0], args[1]);
                default -> {
                    log.error("Неверное число аргументов");
                    return;
                }
            }
            System.out.println("Конвертация завершена успешно");
        } catch (ConverterException exception) {
            log.error("Завершение программы\n");
        }
    }
}
