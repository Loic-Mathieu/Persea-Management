package be.hers.info.persea.util.documents.sheet;

import java.io.File;
import java.io.IOException;

public interface PerseaSheetBuilder {

    /**
     *
     * @param title
     * @return
     */
    PerseaSheetBuilder changeTitle(String title);

    /**
     *
     * @param x
     * @param y
     * @param value
     * @return
     */
    PerseaSheetBuilder append(int x, int y, String value);

    /**
     *
     * @param x
     * @param y
     * @param value
     * @param color
     * @return
     */
    PerseaSheetBuilder append(int x, int y, String value, String color, boolean bold);

    /**
     *
     * @param path
     * @return
     */
    File build(String path) throws IOException;
}
