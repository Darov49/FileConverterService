package ru.vyatsu.fileconverter.factory;

import lombok.experimental.UtilityClass;
import ru.vyatsu.fileconverter.service.ConversionType;
import ru.vyatsu.fileconverter.converter.Converter;
import ru.vyatsu.fileconverter.converter.JsonToXmlConverter;
import ru.vyatsu.fileconverter.converter.XmlToJsonConverter;


@UtilityClass
public class ConverterFactory {
    public Converter createConverter(ConversionType conversionType) {
        return switch (conversionType) {
            case XML_TO_JSON -> new XmlToJsonConverter();
            case JSON_TO_XML -> new JsonToXmlConverter();
        };
    }
}
