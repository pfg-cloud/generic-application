package model.request;

import lombok.Data;

@Data
public class CPULoadRequest {

    private long duration;
    private double loadPercentage;
    private int numberOfCores;
    private int numberOfThreadsPerCore;

}
