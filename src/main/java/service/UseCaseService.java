package service;

import com.martensigwart.fakeload.*;
import common.FileReader;
import io.micrometer.core.instrument.Timer;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Service
public class UseCaseService {

    private static final String DUMMY_FILE_PATH = "file/read.txt";

    @Autowired
    public UseCaseService(final PrometheusMeterRegistry prometheusMeterRegistry) {
        this.prometheusMeterRegistry = prometheusMeterRegistry;
    }

    private final PrometheusMeterRegistry prometheusMeterRegistry;

    private FakeLoadExecutor getFakeLoadExecutor() {
        return FakeLoadExecutors.newDefaultExecutor();
    }

    private void executeLoad(final FakeLoad fakeLoad) {
        getFakeLoadExecutor().execute(fakeLoad);
    }

    public void simulateHighMemoryUsage(final int memoryInMb, final int timeInSeconds) throws InterruptedException {
        final FakeLoad fakeLoad = FakeLoads.create()
                .lasting(timeInSeconds, TimeUnit.SECONDS)
                .withMemory(memoryInMb, MemoryUnit.MB);
        executeLoad(fakeLoad);
    }

    public void simulateHighCPULoad(final long duration, final int loadPercentage) {
        final FakeLoad fakeLoad = FakeLoads.create()
                .lasting(duration, TimeUnit.SECONDS)
                .withCpu(loadPercentage);
        executeLoad(fakeLoad);
    }

    public void simulateRequestLoad() throws InterruptedException {
        Thread.sleep(2000);
    }

    @SuppressWarnings({"MismatchedReadAndWriteOfArray"})
    public void simulateFileRead() throws IOException {
        final long startTime = System.currentTimeMillis();
        final byte[] fileInBytes = FileReader.read(DUMMY_FILE_PATH);
        if (fileInBytes == null) {
            throw new IOException("Could not read file");
        }
        final byte[] write = new byte[fileInBytes.length];
        System.arraycopy(fileInBytes, 0, write, 0, fileInBytes.length);

        final long endTime = System.currentTimeMillis();
        final long duration = (endTime - startTime);
        final Timer timer = prometheusMeterRegistry.timer("memory_file_read_operation", Collections.emptyList());
        timer.record(duration, TimeUnit.MILLISECONDS);
    }
}
