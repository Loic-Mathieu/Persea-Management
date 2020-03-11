package be.hers.info.persea.controller.courtCase;

import be.hers.info.persea.config.AppContext;
import be.hers.info.persea.controller.IController;
import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.view.ManageCourtCaseView;
import be.hers.info.persea.view.PerseaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component(value = "controllerManageCourCases")
public class ManageCourtCasesController implements IController {
    private final ManageCourtCaseView view;
    private final CourtCaseDao courtCaseDAO;

    @Autowired
    public ManageCourtCasesController(ManageCourtCaseView view, CourtCaseDao courtCaseDAO) {
        assert view != null;
        assert courtCaseDAO != null;

        this.view = view;
        this.courtCaseDAO = courtCaseDAO;
    }

    @Override
    public void initView() {
        this.view.getBCreateDocument().addActionListener(listener -> goToCreateDocument());

        PerseaView.getInstance().changeContent(view.getMainPanel());
    }

    private void goToCreateDocument() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);

        CreateDocumentController controller = context.getBean(CreateDocumentController.class);

        controller.setModel(new CourtCase("E42"));
        controller.initView();
    }
}
