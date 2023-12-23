package org.example.file_worker;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.bean.BrandJson;
import org.example.bean.BrandsJson;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@AllArgsConstructor
public class JsonToXmlFileWorker implements FileWorker {
    final String inputFilePath;
    final String outputFilePath;

    @Override
    public List<BrandJson> read() throws IOException {
        try {
            return OBJECT_MAPPER.readValue(new File(inputFilePath), BrandsJson.class).getBrands();
        } catch (IOException fileReadException) {
            throw new IOException("ошибка при считывании файла json - " + inputFilePath, fileReadException);
        }
    }

    @Override
    public void write(final Object laptopsXML) throws IOException {
        try {
            XML_MAPPER.writeValue(new File(outputFilePath), laptopsXML);
        } catch (Exception fileWriteException) {
            throw new IOException("ошибка при записи в файл xml - " + outputFilePath, fileWriteException);
        }
    }
}
