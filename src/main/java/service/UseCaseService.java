package service;

import org.springframework.stereotype.Service;
import usecases.HighCPULoad;
import usecases.MemoryUsage;

@Service
public class UseCaseService {

    public void simulateHighMemoryUsage(final int memoryInMb, final int timeInSeconds) throws InterruptedException {
        MemoryUsage.allocate(memoryInMb);
        Thread.sleep(timeInSeconds * 1000);
        MemoryUsage.clearMemory();
    }

    public void simulateHighCPULoad(final long duration, final double loadPercentage, final int numberOfCores,
                                    final int numberOfThreadsPerCore) {
        final HighCPULoad cpuLoad = new HighCPULoad();
        cpuLoad.execute(duration, loadPercentage, numberOfCores, numberOfThreadsPerCore);
    }
}
