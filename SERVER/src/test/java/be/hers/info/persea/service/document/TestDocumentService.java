package be.hers.info.persea.service.document;

import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.dao.tag.TagDao;

import be.hers.info.persea.dao.user.UserDao;
import org.junit.Before;

import org.mockito.Mockito;

public abstract class TestDocumentService {

    // Service to test
    DocumentService documentService;

    TagDao tagDao;
    CourtCaseDao courtCaseDao;
    UserDao userDao;

    @Before
    public void setupMock() {
        this.courtCaseDao = Mockito.mock(CourtCaseDao.class);
        this.tagDao = Mockito.mock(TagDao.class);
        this.userDao = Mockito.mock(UserDao.class);

        this.documentService = new DocumentServiceImpl(
                this.tagDao,
                this.courtCaseDao,
                this.userDao
        );

    }
}
