package controllers;

import base.BaseController;
import lombok.extern.slf4j.Slf4j;
import model.request.CPULoadRequest;
import model.request.HighMemoryUsageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UseCaseService;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/use-case")
public class UseCasesController extends BaseController {

    @Autowired
    public UseCasesController(final UseCaseService useCaseService) {
        this.useCaseService = useCaseService;
    }

    private final UseCaseService useCaseService;

    @PostMapping(value = "/high-memory-usage", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> simulateHighMemoryUsage(final HighMemoryUsageRequest request) throws InterruptedException {
        useCaseService.simulateHighMemoryUsage(request.getMemoryInMegabytes(), request.getTimeOfExecutionInSeconds());

        return success();
    }

    @PostMapping(value = "/cpu-load", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> simulateCPULoad(final CPULoadRequest request) {
        useCaseService.simulateHighCPULoad(request.getDuration(), request.getLoadPercentage(),
                request.getNumberOfCores(), request.getNumberOfThreadsPerCore());

        return success();
    }

    @PostMapping(value = "/load-request", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> simulateLoadRequest() throws IOException {
        useCaseService.simulateRequestLoad();
        return success();
    }

}
