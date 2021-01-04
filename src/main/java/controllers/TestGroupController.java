package controllers;

import base.BaseController;
import model.request.CreateTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TestGroupService;

@RestController
@RequestMapping("/test-group")
public class TestGroupController extends BaseController {

    @Autowired
    public TestGroupController(final TestGroupService testGroupService) {
        this.testGroupService = testGroupService;
    }

    private final TestGroupService testGroupService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createTestGroup(@RequestBody final CreateTestRequest request) {
        testGroupService.createTest(request);
        return success();
    }
}
