package be.hers.info.persea.request.options;

import lombok.Getter;

@Getter
public class SaveTagRequest {
    // Static
    private String date;

    // dynamic
    private String username;

    // case
    private String caseNumber;
    private String clientName;
    private String clientFirstName;
}
