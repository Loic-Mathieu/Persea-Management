package be.hers.info.persea.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientRequest {
    private String lastName;
    private String firstName;

    private int gender;

    private long address;

    private String phoneNumber;
    private String mail;
}
