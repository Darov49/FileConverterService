package ru.vyatsu.fileconverter;

import lombok.val;
import ru.vyatsu.fileconverter.service.ConversionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConvertersTest {
    @Test
    void testXmlToJsonConvert(@TempDir Path tempDir) throws Exception {
        try (val xmlInputStream = getClass().getClassLoader().getResourceAsStream("data.xml")) {
            // Содержимое входного файла копируется во временный файл - вся последующая работа будет именно с ним
            val tempFile = tempDir.resolve("temp.xml");
            assert xmlInputStream != null;
            Files.copy(xmlInputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

            val outputPath = tempDir.resolve("result.json");
            ConversionService.convert(tempFile.toString(), outputPath.toString());

            val expectedFile = Paths.get(Objects.requireNonNull
                    (getClass().getClassLoader().getResource("data.json")).toURI());

            val inputFileContent = Files.readString(expectedFile).replaceAll("\\s", "");
            val outputFileContent = Files.readString(outputPath).replaceAll("\\s", "");

            assertEquals(inputFileContent, outputFileContent);
        }
    }

    @Test
    void testJsonToXmlConvert(@TempDir Path tempDir) throws Exception {
        try (val jsonInputStream = getClass().getClassLoader().getResourceAsStream("data.json")) {
            // Содержимое входного файла копируется во временный файл - вся последующая работа будет именно с ним
            val tempFile = tempDir.resolve("temp.json");
            assert jsonInputStream != null;
            Files.copy(jsonInputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

            val outputPath = tempDir.resolve("result.xml");
            ConversionService.convert(tempFile.toString(), outputPath.toString());

            val expectedFile = Paths.get(Objects.requireNonNull
                    (getClass().getClassLoader().getResource("data.xml")).toURI());

            val inputFileContent = Files.readString(expectedFile).trim().replaceAll("\\s", "");
            val outputFileContent = Files.readString(outputPath).trim().replaceAll("\\s", "");

            assertEquals(inputFileContent, outputFileContent);
        }
    }

}