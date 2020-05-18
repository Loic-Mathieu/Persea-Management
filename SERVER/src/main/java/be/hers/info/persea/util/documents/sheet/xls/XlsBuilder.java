package be.hers.info.persea.util.documents.sheet.xls;

import be.hers.info.persea.util.documents.sheet.PerseaSheetBuilder;
import lombok.Getter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class XlsBuilder implements PerseaSheetBuilder {

    @Getter
    private static class PerseaCell implements Comparable<PerseaCell> {
        private int x;
        private int y;
        private String value;

        private short color;
        private boolean bold;

        public PerseaCell(int x, int y, String value, short color, boolean bold) {
            this.x = x;
            this.y = y;
            this.value = value;

            this.color = color;
            this.bold = bold;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null) {
                return false;
            } else if (object == this) {
                return true;
            } else if (object instanceof PerseaCell) {
                PerseaCell cell = (PerseaCell)object;
                return cell.getX() == this.x && cell.getY() == this.y;
            }

            return false;
        }

        @Override
        public int compareTo(@NotNull XlsBuilder.PerseaCell cell) {
            if (cell.getY() > this.y || (cell.getY() == this.y && cell.getX() > this.x)) {
                return -1;
            } else if (cell.getY() < this.y || (cell.getY() == this.y && cell.getX() < this.x)) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private String title;
    private Set<PerseaCell> cells;

    public XlsBuilder() {
        this.cells = new HashSet<>();
    }

    @Override
    public PerseaSheetBuilder changeTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public PerseaSheetBuilder append(int x, int y, String value) {
        this.cells.add(new PerseaCell(x, y, value, Short.parseShort("0"), false));
        return this;
    }

    @Override
    public PerseaSheetBuilder append(int x, int y, String value, String color, boolean bold) {
        this.cells.add(new PerseaCell(x, y, value, Short.parseShort(color), bold));
        return this;
    }

    @Override
    public File build(String path) throws IOException {
        // Sort cells, left to right and up to bottom
        List<PerseaCell> sortedCells = new ArrayList<>(cells);
        Collections.sort(sortedCells);

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(title != null ? title : "sheet");

        int line = -1;
        Row activeRow = null;
        for(PerseaCell rawCell : sortedCells) {
            // Check if this is still the same line
            if (rawCell.getY() > line) {
                line = rawCell.getY();
                activeRow = sheet.createRow(rawCell.getY());
            }

            // Font style
            XSSFFont font = workbook.createFont();
            font.setBold(rawCell.isBold());
            font.setColor(rawCell.getColor());

            // Cell style
            CellStyle style = workbook.createCellStyle();
            style.setFont(font);

            // convert raw cell to xls cell
            assert activeRow != null;
            Cell cell = activeRow.createCell(rawCell.getX());
            cell.setCellValue(rawCell.getValue());
            cell.setCellStyle(style);
        }

        File file = new File(path);
        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();

        return file;
    }
}
