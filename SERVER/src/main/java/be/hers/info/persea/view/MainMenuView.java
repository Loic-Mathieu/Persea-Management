package be.hers.info.persea.view;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Getter
@Component(value = "viewMainMenu")
public class MainMenuView {
    private JPanel mainPanel;

    private JButton bClient;
    private JButton bCase;
}
