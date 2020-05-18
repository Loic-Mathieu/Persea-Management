package be.hers.info.persea.util.documents.sheet.xls;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public abstract class PerseaXlsFile {
    /*  STATIC PROPERTIES   */
    public static final String[] DOCUMENT_SUFFIXES = {".xls", "xlsx"};
    public static final String DOCUMENT_DEFAULT_SUFFIX = DOCUMENT_SUFFIXES[0];

    /* Target xls file  */
    protected Workbook workbook;

    protected PerseaXlsFile() {
        Workbook workbook = new XSSFWorkbook();
    }

    protected PerseaXlsFile(File file) throws IOException, InvalidFormatException {
        Workbook workbook = new XSSFWorkbook(OPCPackage.open(file));
    }
}
