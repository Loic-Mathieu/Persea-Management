package be.hers.info.persea.dto.contributor;

import be.hers.info.persea.model.contibutor.Lawyer;
import lombok.Getter;

@Getter
public class LawyerDto {
    private final long id;

    private final String lastName;
    private final String firstName;

    private final int gender;

    private final long address;

    private final String phoneNumber;
    private final String mail;

    public LawyerDto(Lawyer lawyer) {
        this.id = lawyer.getId();

        this.lastName = lawyer.getLastName();
        this.firstName = lawyer.getFirstName();

        this.gender = lawyer.getGender().code;

        this.address = lawyer.getAddress().getId();

        this.phoneNumber = lawyer.getPhoneNumber();
        this.mail = lawyer.getMail();

    }
}
