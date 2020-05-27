package be.hers.info.persea.service.time;

import be.hers.info.persea.request.time.CreateTimePeriodRequest;

public interface TimePeriodService {
    /**
     *
     * @param request
     * @return
     */
    long creatTimePeriod(CreateTimePeriodRequest request);
}
