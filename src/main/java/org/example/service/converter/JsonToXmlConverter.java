package org.example.service.converter;

import lombok.NoArgsConstructor;
import org.example.bean.BrandJson;
import org.example.bean.BrandsJson;
import org.example.bean.LaptopXml;
import org.example.bean.LaptopsXml;
import org.example.exception.ConverterException;
import org.example.mapper.LaptopMapper;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/***
 * Класс для конвертации файла из JSON в XML
 */
@NoArgsConstructor
public class JsonToXmlConverter implements Converter<List<BrandJson>, LaptopsXml> {

    public void convert(final String inputFile, final String outputFile) throws IOException, ConverterException {
        write(outputFile, transform(read(inputFile)));
    }

    @Override
    public List<BrandJson> read(final String inputFilePath) throws IOException {
        try {
            return OBJECT_MAPPER.readValue(new File(inputFilePath), BrandsJson.class).getBrands();
        } catch (IOException fileReadException) {
            throw new IOException("ошибка при считывании файла json - " + inputFilePath, fileReadException);
        }
    }

    @Override
    public LaptopsXml transform(final List<BrandJson> brands) throws ConverterException {
        try {
            return LaptopsXml.builder().laptops(brands.stream()
                    .flatMap(brand -> brand.getLaptops().stream()
                            .map(laptopJSON -> LaptopMapper.INSTANCE.toLaptopXml(laptopJSON, brand)))
                    .sorted(Comparator.comparingInt(LaptopXml::getId))
                    .toList()).build();
        } catch (Exception fileConvertException) {
            throw new ConverterException("Ошибка при конвертировании файла из json в xml", fileConvertException);
        }
    }

    @Override
    public void write(final String outputFilePath, final LaptopsXml laptopsXML) throws IOException {
        try {
            XML_MAPPER.writeValue(new File(outputFilePath), laptopsXML);
        } catch (Exception fileWriteException) {
            throw new IOException("ошибка при записи в файл xml - " + outputFilePath, fileWriteException);
        }
    }
}
