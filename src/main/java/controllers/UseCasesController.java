package controllers;

import base.BaseController;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(value = "/cpu-load", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> simulateCPULoad() throws IOException {
        useCaseService.simulateHighCPULoad();

        return success();
    }

    @PostMapping(value = "/load-request", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> simulateLoadRequest() throws InterruptedException {
        useCaseService.simulateRequestLoad();
        return success();
    }

    @PostMapping(value = "/read-dummy-file", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> simulateReadDummyFile() throws IOException {
        useCaseService.simulateFileRead();
        return success();
    }

}
