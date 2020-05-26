package be.hers.info.persea.request.time;

import lombok.Getter;

@Getter
public class CreateTimePeriodRequest {
    private String date;

    private String startTime;
    private String stopTime;

    private long courtCase;

    private double supplement;

    private String description;

    private String type;
}
