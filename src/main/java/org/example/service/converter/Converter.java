package org.example.service.converter;

import org.example.exception.ConverterException;

import java.io.IOException;

public interface Converter {
    void convert(final String inputFile, final String outputFile) throws ConverterException, IOException;
}
