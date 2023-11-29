package org.example.service.converters;

import org.example.service.ConversionService;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertersTest {
    @Test
    void testXMLtoJSONConvert() throws Exception {
        String outputPath = "src/test/resources/result.json";
        ConversionService.convert("src/test/resources/data.xml", outputPath);

        Path expectedFile = Paths.get("src/test/resources/data.json");
        Path actualFile = Paths.get(outputPath);

        String inputFileContent = Files.readString(expectedFile).replaceAll("\\s", "");
        String outputFileContent = Files.readString(actualFile).replaceAll("\\s", "");

        assertEquals(inputFileContent, outputFileContent);
    }

    @Test
    void testJSONtoXMLConvert() throws Exception {
        String outputPath = "src/test/resources/result.xml";
        ConversionService.convert("src/test/resources/data.json", outputPath);

        Path expectedFile = Paths.get("src/test/resources/data.xml");
        Path actualFile = Paths.get(outputPath);

        String inputFileContent = Files.readString(expectedFile).trim().replaceAll("\\s", "");
        String outputFileContent = Files.readString(actualFile).trim().replaceAll("\\s", "");

        assertEquals(inputFileContent, outputFileContent);
    }

}