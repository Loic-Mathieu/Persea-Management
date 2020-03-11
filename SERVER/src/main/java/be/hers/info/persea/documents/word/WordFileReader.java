package be.hers.info.persea.documents.word;

import be.hers.info.persea.documents.FileReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WordFileReader implements FileReader {

    private XWPFDocument document;

    public WordFileReader(File file) throws InvalidFormatException, IOException{
        this.document = new XWPFDocument(OPCPackage.open(file));
    }

    public WordFileReader(FileInputStream fileInputStream) throws InvalidFormatException, IOException {
        this.document = new XWPFDocument(OPCPackage.open(fileInputStream));
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
