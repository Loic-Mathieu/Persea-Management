package be.hers.info.persea.dao.tag;

import be.hers.info.persea.dao.IDAOCrud;
import be.hers.info.persea.model.document.Tag;

import javax.persistence.EntityNotFoundException;


public interface TagDao extends IDAOCrud<Tag> {
    Tag findByName(String tagName) throws EntityNotFoundException;
}
