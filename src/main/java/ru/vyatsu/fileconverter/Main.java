package ru.vyatsu.fileconverter;

import lombok.val;
import ru.vyatsu.fileconverter.exception.ExceptionHandler;
import ru.vyatsu.fileconverter.service.ConversionService;
import ru.vyatsu.fileconverter.service.MenuService;


public class Main {
    public static void main(String[] args) {
        try {
            switch (args.length) {
                // интерактивный выбор аргументов
                case 0 -> {
                    val fileNames = MenuService.getFileNames(); // получение имен входного и выходного файлов
                    ConversionService.convert(fileNames.getLeft(), fileNames.getRight());
                }
                // аргументы из консоли
                case 2 -> ConversionService.convert(args[0], args[1]);
                default ->
                        throw new IllegalArgumentException(String.format("Неверное число аргументов: %d", args.length));
            }
            System.out.println("Конвертация завершена успешно");
        } catch (Exception exception) {
            ExceptionHandler.handleException(exception);
        }
    }
}
