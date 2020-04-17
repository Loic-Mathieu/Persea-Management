package be.hers.info.persea.documents;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public interface PerseaFileReader {
    String readFile();

    String readHeader();

    String readFooter();
}
