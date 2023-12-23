package org.example.factory;

import lombok.experimental.UtilityClass;
import org.example.file_worker.FileWorker;
import org.example.file_worker.JsonToXmlFileWorker;
import org.example.file_worker.XmlToJsonFileWorker;
import org.example.service.ConversionType;

@UtilityClass
public class FileWorkerFactory {
    public FileWorker createFileWorker(ConversionType conversionType, String inputFile, String outputFile) {
        FileWorker fileWorker = null;
        switch(conversionType) {
            case XML_TO_JSON -> fileWorker = new XmlToJsonFileWorker(inputFile, outputFile);
            case JSON_TO_XML -> fileWorker = new JsonToXmlFileWorker(inputFile, outputFile);
        }

        return fileWorker;
    }
}
