package be.hers.info.persea.request.contributor;

import lombok.Getter;

@Getter
public class CreateOppositionRequest {
    private String lastName;
    private String firstName;

    private int gender;

    private long address;

    private String phoneNumber;
    private String mail;
}
