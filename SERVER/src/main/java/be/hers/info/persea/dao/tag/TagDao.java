package be.hers.info.persea.dao.tag;

import be.hers.info.persea.dao.IDAOCrud;
import be.hers.info.persea.model.document.PerseaProperty;
import be.hers.info.persea.model.document.Tag;

import javax.persistence.EntityNotFoundException;


public interface TagDao extends IDAOCrud<Tag> {
    Tag getByName(String tagName) throws EntityNotFoundException;

    Tag getByProperty(PerseaProperty property);

    String getCurrentValueByProperty(PerseaProperty property);

    void updateName(PerseaProperty property, String newName);
}
