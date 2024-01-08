package org.example.service.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.NoArgsConstructor;
import org.example.bean.BrandJson;
import org.example.bean.BrandsJson;
import org.example.bean.LaptopXml;
import org.example.exception.ConverterException;
import org.example.mapper.LaptopMapper;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Класс для конвертации файла из XML в JSON
 */
@NoArgsConstructor
public class XmlToJsonConverter implements Converter<List<LaptopXml>, BrandsJson> {
    @Override
    public void convert(final String inputFile, final String outputFile) throws IOException, ConverterException {
        write(outputFile, transform(read(inputFile)));
    }

    @Override
    public List<LaptopXml> read(final String inputFilePath) throws IOException {
        try {
            return XML_MAPPER.readValue(new File(inputFilePath), new TypeReference<>() {
            });
        } catch (IOException fileReadException) {
            throw new IOException("ошибка при считывании файла xml - " + inputFilePath, fileReadException);
        }
    }

    @Override
    public BrandsJson transform(final List<LaptopXml> laptops) throws ConverterException {
        try {
            return BrandsJson.builder().brands(laptops.stream().collect(Collectors.groupingBy(LaptopXml::getBrand, Collectors.mapping(LaptopMapper.INSTANCE::toLaptopJson, Collectors.toList()))).entrySet().stream().map(entry -> BrandJson.builder().name(entry.getKey()).laptops(entry.getValue()).build()).sorted(Comparator.comparing(BrandJson::getName)).toList()).build();
        } catch (Exception fileConvertException) {
            throw new ConverterException("Ошибка при конвертировании файла из xml в json", fileConvertException);
        }
    }

    @Override
    public void write(final String outputFilePath, final BrandsJson brandsJSON) throws IOException {
        try {
            OBJECT_MAPPER.writeValue(new File(outputFilePath), brandsJSON);
        } catch (Exception fileWriteException) {
            throw new IOException("ошибка при записи в файл json - " + outputFilePath, fileWriteException);
        }
    }
}

