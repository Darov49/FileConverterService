package org.example.service.converter;

import org.example.exception.ConverterException;

public interface Converter {
    Object convert() throws ConverterException;
}
