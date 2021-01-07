package service;

import common.FileReader;
import org.springframework.stereotype.Service;
import usecases.HighCPULoad;
import usecases.MemoryUsage;

import java.io.IOException;

@Service
public class UseCaseService {

    private static final String DUMMY_FILE_PATH = "/file/read.txt";

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

    public void simulateRequestLoad() throws IOException {
        FileReader.read(DUMMY_FILE_PATH);
    }
}
