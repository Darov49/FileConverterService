package org.example.file_worker;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.bean.LaptopXml;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@AllArgsConstructor
public class XmlToJsonFileWorker implements FileWorker {
    final String inputFilePath;
    final String outputFilePath;

    @Override
    public List<LaptopXml> read() throws IOException {
        try {
            return XML_MAPPER.readValue(new File(inputFilePath), new TypeReference<>() {
            });
        } catch (IOException fileReadException) {
            throw new IOException("ошибка при считывании файла xml - " + inputFilePath, fileReadException);
        }
    }

    @Override
    public void write(final Object brandsJSON) throws IOException {
        try {
            OBJECT_MAPPER.writeValue(new File(outputFilePath), brandsJSON);
        } catch (Exception fileWriteException) {
            throw new IOException("ошибка при записи в файл json - " + outputFilePath, fileWriteException);
        }
    }

}
