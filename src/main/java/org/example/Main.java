package org.example;

import org.example.service.ConverterException;
import org.example.service.converters.Conversion;

public class Main {
    public static void main(String[] args) throws ConverterException {

        if (args.length != 2) {
            System.err.println("Неверное число аргументов");
            return;
        }
        try {
            Conversion.convert(args[0], args[1]);
        } catch (ConverterException e) {
            throw new ConverterException("Завершение программы");
        }
    }
}