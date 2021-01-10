package model.request;

import lombok.Data;

@Data
public class CPULoadRequest {

    private long duration;
    private int loadPercentage;
    private int numberOfCores;
    private int numberOfThreadsPerCore;

}
