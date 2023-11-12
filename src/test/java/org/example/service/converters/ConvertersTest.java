package org.example.service.converters;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConvertersTest {
    @Test
    void testXMLtoJSONConvert () throws Exception {
        XMLtoJSONConverter.convert("src/test/resources/data.xml", "src/test/resources/result.json");

        Path expectedFile = Paths.get("src/test/resources/data.json");
        Path actualFile = Paths.get("src/test/resources/result.json");

        String inputFileContent = Files.readString(expectedFile).replaceAll("\\s", "");
        String outputFileContent = Files.readString(actualFile).replaceAll("\\s", "");

        assertEquals(inputFileContent, outputFileContent);
    }

@Test
    void testJSONtoXMLConvert () throws Exception {
        JSONtoXMLConverter.convert("src/test/resources/data.json", "src/test/resources/result.xml");

        Path expectedFile = Paths.get("src/test/resources/data.xml");
        Path actualFile = Paths.get("src/test/resources/result.xml");

        String inputFileContent = Files.readString(expectedFile).trim().replaceAll("\\s", "");
        String outputFileContent = Files.readString(actualFile).trim().replaceAll("\\s", "");

        assertEquals(inputFileContent, outputFileContent);
    }

}