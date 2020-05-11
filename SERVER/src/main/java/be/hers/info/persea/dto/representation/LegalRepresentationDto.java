package be.hers.info.persea.dto.representation;

import be.hers.info.persea.model.representation.LegalRepresentation;
import lombok.Getter;

@Getter
public class LegalRepresentationDto {

    private final long id;
    private final String subject;
    private final String location;
    private final String date;
    private final long courtCase;

    public LegalRepresentationDto(LegalRepresentation legalRepresentation) {
        this.id = legalRepresentation.getId();
        this.subject = legalRepresentation.getSubject();
        this.location = legalRepresentation.getLocation();
        this.date = legalRepresentation.getDate().toString();
        this.courtCase = legalRepresentation.getCourtCase().getId();
    }
}
