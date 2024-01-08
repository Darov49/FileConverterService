package org.example.service.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.example.exception.ConverterException;

import java.io.IOException;

public interface Converter<T, R> {
    XmlMapper XML_MAPPER = (XmlMapper) new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
    ObjectMapper OBJECT_MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);


    void convert(final String inputFile, final String outputFile) throws ConverterException, IOException;

    T read(final String inputFile) throws IOException;

    R transform(final T objects) throws ConverterException;

    void write(final String outputFilePath, R object) throws IOException;
}
