package ru.vyatsu.fileconverter.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.vyatsu.fileconverter.bean.BrandJson;
import ru.vyatsu.fileconverter.bean.BrandsJson;
import ru.vyatsu.fileconverter.bean.LaptopXml;
import ru.vyatsu.fileconverter.exception.ConverterException;
import ru.vyatsu.fileconverter.mapper.LaptopMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/***
 * Класс для конвертации файла из XML в JSON
 */
public class XmlToJsonConverter implements Converter {
    private final XmlMapper xmlMapper;
    private final ObjectMapper objectMapper;

    public XmlToJsonConverter() {
        xmlMapper = (XmlMapper) new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void convert(final String inputFile, final String outputFile) throws IOException, ConverterException {
        write(outputFile, transform(read(inputFile)));
    }

    public List<LaptopXml> read(final String inputFilePath) throws IOException {
        try {
            return xmlMapper.readValue(new File(inputFilePath), new TypeReference<>() {
            });
        } catch (IOException fileReadException) {
            throw new IOException("ошибка при считывании файла xml - " + inputFilePath, fileReadException);
        }
    }

    public BrandsJson transform(final List<LaptopXml> laptops) throws ConverterException {
        try {
            return BrandsJson.builder().brands(laptops.stream()
                    .collect(groupingBy(LaptopXml::getBrand,
                            mapping(LaptopMapper.INSTANCE::toLaptopJson, toList())))
                    .entrySet().stream()
                    .map(entry -> BrandJson.builder().name(entry.getKey())
                            .laptops(entry.getValue())
                            .build())
                    .sorted(comparing(BrandJson::getName))
                    .toList()).build();
        } catch (Exception fileConvertException) {
            throw new ConverterException("ошибка при конвертировании файла из xml в json", fileConvertException);
        }
    }

    public void write(final String outputFilePath, final BrandsJson brandsJSON) throws IOException {
        try {
            objectMapper.writeValue(new File(outputFilePath), brandsJSON);
        } catch (Exception fileWriteException) {
            throw new IOException("ошибка при записи в файл json - " + outputFilePath, fileWriteException);
        }
    }
}

