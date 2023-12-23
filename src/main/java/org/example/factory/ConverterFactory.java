package org.example.factory;

import lombok.experimental.UtilityClass;
import org.example.bean.BrandJson;
import org.example.bean.LaptopXml;
import org.example.service.ConversionType;
import org.example.service.converter.Converter;
import org.example.service.converter.JsonToXmlConverter;
import org.example.service.converter.XmlToJsonConverter;

import java.util.List;

@UtilityClass
public class ConverterFactory {
    @SuppressWarnings("unchecked")
    public Converter createConverter(ConversionType conversionType, List<?> data) {
        Converter converter = null;
        switch (conversionType) {
            case XML_TO_JSON -> converter = new XmlToJsonConverter((List<LaptopXml>) data);
            case JSON_TO_XML -> converter = new JsonToXmlConverter((List<BrandJson>) data);
        }

        return converter;
    }
}
