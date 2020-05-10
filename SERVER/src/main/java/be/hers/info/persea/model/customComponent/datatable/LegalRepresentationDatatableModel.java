package be.hers.info.persea.model.customComponent.datatable;

import be.hers.info.persea.model.representation.LegalRepresentation;

import java.util.ArrayList;
import java.util.List;

public class LegalRepresentationDatatableModel extends MyDatatableModel<LegalRepresentation> {

    public LegalRepresentationDatatableModel() {
        super(new ArrayList<>());
        this.columnNames = new String[] {"Objet", "Emplacement", "Date"};
    }

    public LegalRepresentationDatatableModel(List<LegalRepresentation> data) {
        super(data);
        this.columnNames = new String[] {"Objet", "Emplacement", "Date"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0: return this.data.get(rowIndex).getSubject();

            case 1: return this.data.get(rowIndex).getLocation();

            case 2: return this.data.get(rowIndex).getDate();

            default: return null;
        }
    }
}
