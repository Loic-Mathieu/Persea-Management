package be.hers.info.persea.request;

import lombok.Getter;

@Getter
public class CreateCourtCaseRequest {
    private long mainClient;
    private long mainOpposition;
    private long mainLawyer;
}
