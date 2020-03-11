package be.hers.info.persea.documents;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public interface FileReader {
    String readFile();

    String readHeader();

    String readFooter();
}
