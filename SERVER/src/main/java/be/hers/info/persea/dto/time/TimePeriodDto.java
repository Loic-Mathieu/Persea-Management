package be.hers.info.persea.dto.time;

import be.hers.info.persea.model.time.TimePeriod;
import be.hers.info.persea.util.time.PerseaTime;
import lombok.Getter;

@Getter
public class TimePeriodDto {

    private final long id;

    // dd/MM/yyyy
    private final String date;

    // HH:mm
    private final String startTime;
    private final String stopTime;

    private final String description;

    private final String type;

    private final boolean billed;
    private final long bill;

    private final long courtCase;
    private final long owner;

    public TimePeriodDto(TimePeriod timePeriod) {
        this.id = timePeriod.getId();

        this.date = PerseaTime.getStandardFormattedDate(timePeriod.getStartTime());

        this.startTime = PerseaTime.getStandardFormattedHour(timePeriod.getStartTime());
        this.stopTime = PerseaTime.getStandardFormattedHour(timePeriod.getStopTime());

        this.description = timePeriod.getDescription();

        this.type = timePeriod.getPeriodType().name();

        this.billed = timePeriod.isBilled();
        this.bill = timePeriod.getRelatedBill() != null ? timePeriod.getRelatedBill().getId() : 0L;

        this.courtCase = timePeriod.getCourtCase().getId();
        this.owner = timePeriod.getOwner().getId();
    }
}
