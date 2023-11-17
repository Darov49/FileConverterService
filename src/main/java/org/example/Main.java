package org.example;

import org.example.service.ConverterException;
import org.example.service.converters.XMLtoJSONConverter;
import org.example.service.converters.JSONtoXMLConverter;

public class Main {
    public static void main(String[] args) throws ConverterException {

        if (!checkArgs(args)) {
            System.err.println("Некорректный ввод!");
            return;
        }
        try {
            if (args[0].endsWith(".xml")) {
                XMLtoJSONConverter.convert(args[0], args[1]);
            } else {
                JSONtoXMLConverter.convert(args[0], args[1]);
            }
        } catch (ConverterException e) {
            throw new ConverterException("Завершение программы");
        }
    }

    private static boolean checkArgs(String[] args) {
        if (args.length != 2) {
            return false;
        }
        if (!(args[0].endsWith(".xml") || args[0].endsWith(".json")) ||
                !(args[1].endsWith(".xml") || args[1].endsWith(".json"))) {
            return false;
        }
        String inputFileExtension = args[0].substring(args[0].lastIndexOf('.'), args[0].length() - 1);
        String outputFileExtension = args[1].substring(args[1].lastIndexOf('.'), args[1].length() - 1);
        return !inputFileExtension.equals(outputFileExtension);
    }
}