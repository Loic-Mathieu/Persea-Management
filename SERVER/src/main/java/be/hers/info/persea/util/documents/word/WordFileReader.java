package be.hers.info.persea.util.documents.word;

import be.hers.info.persea.util.documents.PerseaFileReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.File;
import java.io.IOException;

public class WordFileReader extends PerseaWordFileUtil implements PerseaFileReader {

    public WordFileReader(File file) throws InvalidFormatException, IOException {
        super(file);
    }

    @Override
    public String readFile() {
        XWPFWordExtractor extractor = new XWPFWordExtractor(document);
        return extractor.getText();
    }

    @Override
    public String readHeader() {
        XWPFWordExtractor extractor = new XWPFWordExtractor(document);
        return null;
    }

    @Override
    public String readFooter() {
        XWPFWordExtractor extractor = new XWPFWordExtractor(document);
        return null;
    }
}
