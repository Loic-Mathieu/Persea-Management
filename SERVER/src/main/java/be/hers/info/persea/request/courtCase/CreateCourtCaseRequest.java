package be.hers.info.persea.request.courtCase;

import lombok.Getter;

@Getter
public class CreateCourtCaseRequest {
    private long mainClient;
    private long mainOpposition;
    private long mainLawyer;
}
