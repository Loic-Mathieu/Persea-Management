package be.hers.info.persea.model.customComponent.datatable;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public abstract class MyDatatableModel<T> extends AbstractTableModel {

    protected List<T> data;
    protected String[] columnNames;

    public MyDatatableModel(List<T> data) {
        this.data = data;
        this.columnNames = new String[0];
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);

    public boolean addRow(T row) {
        return this.data.add(row);
    }

    public boolean addRows(List<T> rows) {
        return this.data.addAll(rows);
    }
}
