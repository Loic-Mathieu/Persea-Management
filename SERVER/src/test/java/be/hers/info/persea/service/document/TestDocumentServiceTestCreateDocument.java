package be.hers.info.persea.service.document;

import be.hers.info.persea.util.documents.word.WordFileWriter;
import be.hers.info.persea.exceptions.TagCreationException;
import be.hers.info.persea.model.user.User;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.document.PerseaProperty;
import be.hers.info.persea.model.document.Tag;
import be.hers.info.persea.util.time.PerseaDate;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

public class TestDocumentServiceTestCreateDocument extends TestDocumentService {

    final long CASE_ID = 10L;

    MultipartFile file;

    @Before
    @SneakyThrows
    public void createFile() {
        this.file = Mockito.mock(MultipartFile.class);

        final String text = "SOME TEXT <<A>> <<B>> <<C>>";

        WordFileWriter fileWriter = new WordFileWriter();
        fileWriter.append(text);

        Mockito.when(this.file.getBytes()).thenReturn(fileWriter.getOutputStream().toByteArray());
    }

    @Test
    public void testCreation() {

        // COURT CASE
        CourtCase courtCase = new CourtCase();
        courtCase.setCaseNumber("LBMF_20200101");
        Mockito.when(this.courtCaseDao.getById(CASE_ID)).thenReturn(courtCase);

        User user = new User();
        user.setLastName("TEST_NAME");
        // TODO change this with new service
        Mockito.when(this.userDao.getById(Mockito.anyLong())).thenReturn(user);

        // CASE TAG
        Tag tag_a = new Tag("A", PerseaProperty.CASE_NUMBER);
        Mockito.when(this.tagDao.getByName("A")).thenReturn(tag_a);

        // DYNAMIC TAG
        Tag tag_b = new Tag("B", PerseaProperty.CURRENT_DATE);
        Mockito.when(this.tagDao.getByName("B")).thenReturn(tag_b);

        // STATIC TAG
        Tag tag_c = new Tag("C", PerseaProperty.USER_NAME);
        Mockito.when(this.tagDao.getByName("C")).thenReturn(tag_c);

        try {
            String result = this.documentService.createDocument(this.file, CASE_ID);

            // expected result
            String expected = String.format("SOME TEXT %s %s %s",
                    courtCase.getCaseNumber(),
                    PerseaDate.getStandardFormattedDate(),
                    user.getLastName()
            );

            Assert.assertEquals(result, expected);
        } catch (TagCreationException e) {
            e.printStackTrace();
        }
    }
}
