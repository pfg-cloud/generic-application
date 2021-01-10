package service;

import com.martensigwart.fakeload.FakeLoad;
import com.martensigwart.fakeload.FakeLoadExecutor;
import com.martensigwart.fakeload.FakeLoadExecutors;
import com.martensigwart.fakeload.FakeLoads;
import common.FileReader;
import org.springframework.stereotype.Service;
import usecases.MemoryUsage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class UseCaseService {

    private static final String DUMMY_FILE_PATH = "/file/read.txt";

    public void simulateHighMemoryUsage(final int memoryInMb, final int timeInSeconds) throws InterruptedException {
        MemoryUsage.allocate(memoryInMb);
        Thread.sleep(timeInSeconds * 1000);
        MemoryUsage.clearMemory();
    }

    public void simulateHighCPULoad(final long duration, final int loadPercentage, final int numberOfCores,
                                    final int numberOfThreadsPerCore) {
//        final HighCPULoad cpuLoad = new HighCPULoad();
//        cpuLoad.execute(duration, loadPercentage, numberOfCores, numberOfThreadsPerCore);
        final FakeLoad fakeLoad = FakeLoads.create()
                .lasting(duration, TimeUnit.SECONDS)
                .withCpu(loadPercentage);
        final FakeLoadExecutor executor = FakeLoadExecutors.newDefaultExecutor();
        executor.execute(fakeLoad);
    }

    public void simulateRequestLoad() throws IOException {
        FileReader.read(DUMMY_FILE_PATH);
    }
}
