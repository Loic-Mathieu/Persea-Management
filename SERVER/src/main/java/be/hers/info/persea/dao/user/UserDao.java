package be.hers.info.persea.dao.user;

import be.hers.info.persea.dao.IDAOCrud;
import be.hers.info.persea.model.user.User;

public interface UserDao extends IDAOCrud<User> {
    /**
     *
     * @param userId
     * @return
     */
    int getCasesCount(long userId);
}
