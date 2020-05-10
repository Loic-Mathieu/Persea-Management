package be.hers.info.persea.dto.contributor;

import be.hers.info.persea.model.contibutor.Opposition;
import lombok.Getter;

@Getter
public class OppositionDto {
    private final long id;

    private final String lastName;
    private final String firstName;

    private final int gender;

    private final long address;

    private final String phoneNumber;
    private final String mail;

    public OppositionDto(Opposition opposition) {
        this.id = opposition.getId();

        this.lastName = opposition.getLastName();
        this.firstName = opposition.getFirstName();

        this.gender = opposition.getGender().code;

        this.address = opposition.getAddress().getId();

        this.phoneNumber = opposition.getPhoneNumber();
        this.mail = opposition.getMail();
    }
}
