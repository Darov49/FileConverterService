package org.example.service.converter;

import org.example.service.ConversionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertersTest {
    @Test
    void testXmlToJsonConvert(@TempDir Path tempDir) throws Exception {
        String xmlResourcePath = "data.xml";
        try (var xmlInputStream = getClass().getClassLoader().getResourceAsStream(xmlResourcePath)) {
            // Содержимое входного файла копируется во временный файл - вся последующая работа будет именно с ним
            Path tempFile = tempDir.resolve("temp.xml");
            Files.copy(xmlInputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

            Path outputPath = tempDir.resolve("result.json");
            ConversionService.convert(tempFile.toString(), outputPath.toString());

            String jsonResourcePath = "data.json";
            Path expectedFile = Paths.get(getClass().getClassLoader().getResource(jsonResourcePath).toURI());

            String inputFileContent = Files.readString(expectedFile).replaceAll("\\s", "");
            String outputFileContent = Files.readString(outputPath).replaceAll("\\s", "");

            assertEquals(inputFileContent, outputFileContent);
        }
    }

    @Test
    void testJsonToXmlConvert(@TempDir Path tempDir) throws Exception {
        String jsonResourcePath = "data.json";
        try (var jsonInputStream = getClass().getClassLoader().getResourceAsStream(jsonResourcePath)) {
            // Содержимое входного файла копируется во временный файл - вся последующая работа будет именно с ним
            Path tempFile = tempDir.resolve("temp.json");
            Files.copy(jsonInputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

            Path outputPath = tempDir.resolve("result.xml");
            ConversionService.convert(tempFile.toString(), outputPath.toString());

            String xmlResourcePath = "data.xml";
            Path expectedFile = Paths.get(getClass().getClassLoader().getResource(xmlResourcePath).toURI());

            String inputFileContent = Files.readString(expectedFile).trim().replaceAll("\\s", "");
            String outputFileContent = Files.readString(outputPath).trim().replaceAll("\\s", "");

            assertEquals(inputFileContent, outputFileContent);
        }
    }

}