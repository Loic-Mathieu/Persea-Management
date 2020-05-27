package be.hers.info.persea.service.options;

import be.hers.info.persea.dao.tag.TagDao;
import be.hers.info.persea.dto.options.TagOptions;
import be.hers.info.persea.model.document.PerseaProperty;
import be.hers.info.persea.request.options.SaveTagRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "serviceOptions")
public class OptionServiceImpl implements OptionService {

    private final TagDao tagDao;

    @Autowired
    public OptionServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public TagOptions getTagOptions() {
        TagOptions tagOptions = new TagOptions();

        // dynamic
        tagOptions.setDate(this.tagDao.getCurrentValueByProperty(PerseaProperty.CURRENT_DATE));

        // user
        tagOptions.setUsername(this.tagDao.getCurrentValueByProperty(PerseaProperty.USER_NAME));

        // case
        tagOptions.setCaseNumber(this.tagDao.getCurrentValueByProperty(PerseaProperty.CASE_NUMBER));
        tagOptions.setClientName(this.tagDao.getCurrentValueByProperty(PerseaProperty.MAIN_CLIENT_NAME));
        tagOptions.setClientFirstName(this.tagDao.getCurrentValueByProperty(PerseaProperty.MAIN_CLIENT_FIRST_NAME));

        return tagOptions;
    }

    @Override
    public void saveTagOptions(SaveTagRequest request) {
        // dynamic
        tagDao.updateName(PerseaProperty.CURRENT_DATE, request.getDate());

        // user
        tagDao.updateName(PerseaProperty.USER_NAME, request.getUsername());

        // case
        tagDao.updateName(PerseaProperty.CASE_NUMBER, request.getCaseNumber());
        tagDao.updateName(PerseaProperty.MAIN_CLIENT_NAME, request.getClientName());
        tagDao.updateName(PerseaProperty.MAIN_CLIENT_FIRST_NAME, request.getClientFirstName());
    }
}
