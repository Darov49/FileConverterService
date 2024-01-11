package org.example.factory;

import lombok.experimental.UtilityClass;
import org.example.service.ConversionType;
import org.example.service.converter.Converter;
import org.example.service.converter.JsonToXmlConverter;
import org.example.service.converter.XmlToJsonConverter;


@UtilityClass
public class ConverterFactory {
    public Converter createConverter(ConversionType conversionType) {
        return switch (conversionType) {
            case XML_TO_JSON -> new XmlToJsonConverter();
            case JSON_TO_XML -> new JsonToXmlConverter();
        };
    }
}
