package model.request;

import lombok.Data;

@Data
public class HighMemoryUsageRequest {

    private int memoryInMegabytes;
    private int timeOfExecutionInSeconds;

}
