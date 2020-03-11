package be.hers.info.persea.view;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Getter
@Component(value = "viewCreateDocument")
public class CreateDocumentView {
    private JPanel mainPanel;
    private JTextArea documentDraft;
    private JButton bLoadFile;
    private JButton bExport;
    private JButton bPreview;
    private JPanel ActionPanel;
    private JPanel TextPanel;
}
