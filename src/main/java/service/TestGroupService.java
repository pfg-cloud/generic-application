package service;

import model.request.CreateTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.TestGroupRepository;

@Service
public class TestGroupService {

    @Autowired
    public TestGroupService(final TestGroupRepository testGroupRepository) {
        this.testGroupRepository = testGroupRepository;
    }

    private final TestGroupRepository testGroupRepository;

    public void createTest(final CreateTestRequest request) {
        // TODO Generate UUID test group

        // TODO create database for use cases


    }
}
