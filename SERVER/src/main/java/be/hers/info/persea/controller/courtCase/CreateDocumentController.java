package be.hers.info.persea.controller.courtCase;

import be.hers.info.persea.controller.IController;
import be.hers.info.persea.dao.TagDao;
import be.hers.info.persea.documents.FileReader;
import be.hers.info.persea.documents.word.WordFileReader;
import be.hers.info.persea.exceptions.InvalidTagException;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.document.Tag;
import be.hers.info.persea.view.CreateDocumentView;
import be.hers.info.persea.view.PerseaView;
import lombok.Setter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CreateDocumentController implements IController {
    private final CreateDocumentView view;

    private final TagDao tagDao;
    private final Pattern TAG_PATTERN;

    @Setter
    private CourtCase model;

    @Autowired
    public CreateDocumentController(@Qualifier("daoTag") TagDao tagDao, CreateDocumentView view) {
        this.tagDao = tagDao;
        this.view = view;

        this.TAG_PATTERN = Pattern.compile("<<(.+?)>>", Pattern.DOTALL);
    }

    @Override
    public void initView() {
        this.view.getBLoadFile().addActionListener(listener -> loadFile());
        this.view.getBPreview().addActionListener(listener -> applyCaseInformations());
        this.view.getBExport().addActionListener(listener -> exportToPdf());

        PerseaView.getInstance().changeContent(view.getMainPanel());
    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("word files", "docx"));

        File selectedFile;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();

            try {
                FileReader fr = new WordFileReader(selectedFile);
                this.view.getDocumentDraft().setText(fr.readFile());
            } catch (InvalidFormatException e) {
                // TODO inform user
                System.out.println("Bad format file");
                e.printStackTrace();
            } catch (IOException e) {
                // TODO inform user
                e.printStackTrace();
                System.out.println("Input Output exception");
            }
        }
    }

    // TODO move that to business
    private String translateTags(final Tag tag) {
        switch (tag.getProprety()) {
            case caseNumber:
                return model.getCaseNumber();
            case clientName:
                // return model.getClient().getName();
            case clientFirstName:
                // return model.getClient().getFirstName();
            case currentDate:
                return Calendar.getInstance().getTime().toString();
            default:
                return null;
        }
    }

    private List<Tag> getTags(String rawText) throws InvalidTagException {
        Matcher matcher = this.TAG_PATTERN.matcher(rawText);
        List<Tag> tags = new ArrayList<>();

        try {
            Set<String> rawTags = new HashSet<>();
            while (matcher.find()) {
                rawTags.add(matcher.group(1));
            }

            rawTags.forEach(rawTag -> tags.add(tagDao.findByName(rawTag)));
            return tags;
        } catch (EntityNotFoundException e) {
            throw new InvalidTagException("tag" + matcher.group(1) + " not found");
        }
    }

    private void applyCaseInformations() {
        if (model == null) {
            throw new NullPointerException("There is no model that can be applied");
        }

        try {
            getTags(view.getDocumentDraft().getText()).forEach(tag -> {
                System.out.println("Prop " + tag.getProprety());
                System.out.println("Value " + translateTags(tag));
            });
        } catch (InvalidTagException e) {
            // TODO inform user
        }
    }

    private void exportToPdf() {
        throw new UnsupportedOperationException();
    }
}
