package be.hers.info.persea.request.representation;

import lombok.Getter;

@Getter
public class CreateLegalRepresentationRequest {
    private String subject;
    private String location;
    private String date;
    private long courtCase;
}
