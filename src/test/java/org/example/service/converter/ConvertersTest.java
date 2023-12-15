package org.example.service.converter;

import org.example.service.ConversionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertersTest {
    @Test
    void testXmlToJsonConvert(@TempDir Path tempDir) throws Exception {
        Path outputPath = tempDir.resolve("result.json");
        ConversionService.convert("src/test/resources/data.xml", outputPath.toString());

        Path expectedFile = Paths.get("src/test/resources/data.json");

        String inputFileContent = Files.readString(expectedFile).replaceAll("\\s", "");
        String outputFileContent = Files.readString(outputPath).replaceAll("\\s", "");

        assertEquals(inputFileContent, outputFileContent);
    }

    @Test
    void testJsonToXmlConvert(@TempDir Path tempDir) throws Exception {
        Path outputPath = tempDir.resolve("result.xml");
        ConversionService.convert("src/test/resources/data.json", outputPath.toString());

        Path expectedFile = Paths.get("src/test/resources/data.xml");

        String inputFileContent = Files.readString(expectedFile).trim().replaceAll("\\s", "");
        String outputFileContent = Files.readString(outputPath).trim().replaceAll("\\s", "");

        assertEquals(inputFileContent, outputFileContent);
    }

}