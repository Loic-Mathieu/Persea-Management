package be.hers.info.persea.util.documents.text.word;

import be.hers.info.persea.util.documents.text.PerseaFileWriter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.IOException;


public class WordFileWriter extends PerseaWordFileUtil implements PerseaFileWriter {

    public WordFileWriter() {
        super();
    }

    public WordFileWriter(File file) throws InvalidFormatException, IOException {
        super(file);
    }

    @Override
    public void append(String text) {
        XWPFParagraph tmpParagraph = document.createParagraph();
        XWPFRun tmpRun = tmpParagraph.createRun();
        tmpRun.setText(text);
        tmpRun.setFontSize(STANDARD_FONT_SIZE);
    }
}