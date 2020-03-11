package be.hers.info.persea.view;

import be.hers.info.persea.config.AppContext;
import be.hers.info.persea.config.RessourceConfig;
import be.hers.info.persea.controller.IController;
import be.hers.info.persea.controller.courtCase.ManageCourtCasesController;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Properties;

public class PerseaView {
    private static PerseaView instance = null;

    @Getter
    private JFrame mainView;
    private JPanel contentPanel;

    private PerseaView() {
        int height;
        int width;

        try {
            Properties props = PropertiesLoaderUtils.loadProperties(RessourceConfig.getInstance().getResource());

            width = Integer.parseInt(props.getProperty("application.display.width"));
            height = Integer.parseInt(props.getProperty("application.display.height"));
        } catch (IOException e) {
            // Todo : create config file
            e.printStackTrace();
            width = 500;
            height = 600;
        }

        this.mainView = new JFrame("Persea");
        this.mainView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainView.setSize(new Dimension(width, height));
        this.mainView.setLayout(new BorderLayout());
        this.initView();
    }

    public static PerseaView getInstance() {
        if(instance == null)
            PerseaView.instance = new PerseaView();

        return PerseaView.instance;
    }

    public void changeContent(JComponent component) {
        this.contentPanel.removeAll();
        this.contentPanel.revalidate();
        this.contentPanel.repaint();

        this.contentPanel.add(component);
    }

    private void initView() {
        JMenu menu = new JMenu("Menu"), submenu = new JMenu("Sub Menu");

        menu.add(new JMenuItem("Item 1"));
        menu.add(new JMenuItem("Item 2"));
        menu.add(new JMenuItem("Item 3"));

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        this.mainView.setJMenuBar(menuBar);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 5));

        JButton bManageCases = new JButton("Gérer les affaires");
        bManageCases.addActionListener(listener -> goToCases());
        JButton bManageClients = new JButton("Gérer les clients");
        bManageClients.addActionListener(listener -> goToClients());

        actionPanel.add(bManageCases);
        actionPanel.add(bManageClients);
        this.mainView.add(actionPanel, BorderLayout.NORTH);

        this.contentPanel = new JPanel();
        this.contentPanel.setLayout(new GridLayout(1, 1));

        this.mainView.add(contentPanel, BorderLayout.CENTER);
    }

    private void goToCases() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

        IController controller = context.getBean(ManageCourtCasesController.class);
        controller.initView();
    }

    private void goToClients() {

    }

    public void setVisible(boolean visible) {
        this.mainView.setVisible(visible);
    }
}
