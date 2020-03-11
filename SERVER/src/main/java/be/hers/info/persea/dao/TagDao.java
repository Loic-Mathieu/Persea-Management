package be.hers.info.persea.dao;

import be.hers.info.persea.model.document.Tag;

import javax.persistence.EntityNotFoundException;


public interface TagDao extends IDAOCrud<Tag> {
    Tag findByName(String tagName) throws EntityNotFoundException;
}
