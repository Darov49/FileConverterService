package org.example.file_worker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

public interface FileWorker {
    XmlMapper XML_MAPPER = (XmlMapper) new XmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
    ObjectMapper OBJECT_MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);


    Object read() throws IOException;

    void write(Object object) throws IOException;
}
