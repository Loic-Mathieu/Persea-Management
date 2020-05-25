package be.hers.info.persea.request.time;

import lombok.Getter;

@Getter
public class CreateTimePeriodRequest {
    String date;

    int hourStart;
    int minuteStart;

    int hourEnd;
    int minuteEnd;

    long caseId;

    String description;
}
