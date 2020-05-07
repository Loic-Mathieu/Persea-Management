package be.hers.info.persea.util.documents;

public interface PerseaFileReader {
    String readFile();

    String readHeader();

    String readFooter();
}
