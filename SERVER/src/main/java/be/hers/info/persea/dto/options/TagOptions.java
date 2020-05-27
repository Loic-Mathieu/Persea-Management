package be.hers.info.persea.dto.options;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TagOptions {
    // Static
    private String date;

    // dynamic
    private String username;

    // case
    private String caseNumber;
    private String clientName;
    private String clientFirstName;
}
