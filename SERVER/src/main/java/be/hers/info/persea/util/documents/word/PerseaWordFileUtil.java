package be.hers.info.persea.util.documents.word;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

abstract class PerseaWordFileUtil {

    /*  STATIC PROPERTIES   */
    public static final String[] DOCUMENT_SUFFIXES = {".docx", ".odt"};
    public static final String DOCUMENT_DEFAULT_SUFFIX = DOCUMENT_SUFFIXES[0];

    /*  SHARED PROPERTIES   */
    protected final int STANDARD_FONT_SIZE = 18;

    // target word document
    protected XWPFDocument document;

    /*  CONSTRUCTORS    */
    /**
     * Create a new WordFile util
     */
    protected PerseaWordFileUtil() {
        this.document = new XWPFDocument();
        this.document.getDocument().getBody().addNewSectPr();
    }

    /**
     * Create a new WordFile util
     * @param file target file
     * @throws InvalidFormatException Bad file format
     * @throws IOException Can not open file
     */
    protected PerseaWordFileUtil(File file) throws InvalidFormatException, IOException {
        this.document = new XWPFDocument(OPCPackage.open(file));
    }

    /* GETTERS  */
    /**
     * Create an input stream based on the file
     * @return the file stream
     * @throws IOException Can not open file
     */
    public ByteArrayOutputStream getOutputStream() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        this.document.write(outputStream);
        return outputStream;
    }

}
