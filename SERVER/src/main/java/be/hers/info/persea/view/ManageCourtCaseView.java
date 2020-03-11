package be.hers.info.persea.view;


import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component(value = "viewManageCourtCase")
@Getter
public class ManageCourtCaseView {
    private JPanel mainPanel;
    private JButton bCreateCase;
    private JList caseList;
    private JButton bLegalRepresentations;
    private JButton bIntervenants;
    private JButton bCloseCase;
    private JButton bCreateReciept;
    private JLabel caseNumber;
    private JButton bAdjustCasePrice;
    private JButton bCreateDocument;
    private JPanel mainInformationsPanel;
    private JPanel actionPanel;
    private JPanel datesPanel;

    private void createUIComponents() {

    }
}
