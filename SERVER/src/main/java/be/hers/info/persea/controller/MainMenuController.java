package be.hers.info.persea.controller;

import be.hers.info.persea.config.AppContext;
import be.hers.info.persea.controller.courtCase.ManageCourtCasesController;
import be.hers.info.persea.view.MainMenuView;
import be.hers.info.persea.view.PerseaView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController implements IController {

    private final MainMenuView view;

    public MainMenuController(MainMenuView view) {
        this.view = view;
    }

    @Override
    public void initView() {
        this.view.getBCase().addActionListener(listener -> goToCases());
        this.view.getBClient().addActionListener(listener -> goToClients());

        PerseaView.getInstance().changeContent(view.getMainPanel());
    }

    private void goToCases() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

        IController controller = context.getBean(ManageCourtCasesController.class);
        controller.initView();
    }

    private void goToClients() {

    }
}
