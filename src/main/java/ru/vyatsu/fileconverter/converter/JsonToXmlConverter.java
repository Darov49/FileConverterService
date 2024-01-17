package ru.vyatsu.fileconverter.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.vyatsu.fileconverter.bean.BrandJson;
import ru.vyatsu.fileconverter.bean.BrandsJson;
import ru.vyatsu.fileconverter.bean.LaptopXml;
import ru.vyatsu.fileconverter.bean.LaptopsXml;
import ru.vyatsu.fileconverter.exception.ConverterException;
import ru.vyatsu.fileconverter.mapper.LaptopMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Comparator.comparingInt;

/***
 * Класс для конвертации файла из JSON в XML
 */

public class JsonToXmlConverter implements Converter {
    private final XmlMapper xmlMapper;
    private final ObjectMapper objectMapper;

    public JsonToXmlConverter() {
        xmlMapper = (XmlMapper) new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void convert(final String inputFile, final String outputFile) throws IOException, ConverterException {
        write(outputFile, transform(read(inputFile)));
    }

    public List<BrandJson> read(final String inputFilePath) throws IOException {
        try {
            return objectMapper.readValue(new File(inputFilePath), BrandsJson.class).getBrands();
        } catch (IOException fileReadException) {
            throw new IOException("ошибка при считывании файла json - " + inputFilePath, fileReadException);
        }
    }

    public LaptopsXml transform(final List<BrandJson> brands) throws ConverterException {
        try {
            return LaptopsXml.builder().laptops(brands.stream()
                    .flatMap(brand -> brand.getLaptops().stream()
                            .map(laptopJSON -> LaptopMapper.INSTANCE.toLaptopXml(laptopJSON, brand)))
                    .sorted(comparingInt(LaptopXml::getId))
                    .toList()).build();
        } catch (Exception fileConvertException) {
            throw new ConverterException("ошибка при конвертировании файла из json в xml", fileConvertException);
        }
    }

    public void write(final String outputFilePath, final LaptopsXml laptopsXML) throws IOException {
        try {
            xmlMapper.writeValue(new File(outputFilePath), laptopsXML);
        } catch (Exception fileWriteException) {
            throw new IOException("ошибка при записи в файл xml - " + outputFilePath, fileWriteException);
        }
    }
}
