package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.FileReader;
import io.micrometer.core.instrument.Timer;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UseCaseService {

    private static final String DUMMY_FILE_PATH = "file/read.txt";
    private static final String LARGE_VECTOR_FILE_PATH = "file/vector.json";

    @Autowired
    public UseCaseService(final PrometheusMeterRegistry prometheusMeterRegistry) {
        this.prometheusMeterRegistry = prometheusMeterRegistry;
    }

    private final PrometheusMeterRegistry prometheusMeterRegistry;

    public void simulateHighCPULoad() throws IOException {
        final byte[] vectorBytes = FileReader.read(LARGE_VECTOR_FILE_PATH);

        ObjectMapper objectMapper = new ObjectMapper();
        final Integer[] vector = objectMapper.readValue(vectorBytes, Integer[].class);
        final List<Integer> list = Arrays.asList(vector);

        list.sort(Integer::compareTo);
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
