package ru.vyatsu.fileconverter;

import ru.vyatsu.fileconverter.exception.ConverterException;

import java.io.IOException;

public interface Converter {
    void convert(final String inputFile, final String outputFile) throws ConverterException, IOException;
}
