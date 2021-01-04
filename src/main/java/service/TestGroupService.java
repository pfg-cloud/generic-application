package service;

import configuration.MetricsConfiguration;
import lombok.extern.slf4j.Slf4j;
import model.entity.TestGroup;
import model.request.CreateTestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import repositories.TestGroupRepository;

import java.util.UUID;

@Slf4j
@Service
public class TestGroupService {

    @Autowired
    public TestGroupService(final TestGroupRepository testGroupRepository,
                            final MetricsConfiguration metricsConfiguration) {
        this.testGroupRepository = testGroupRepository;
        this.metricsConfiguration = metricsConfiguration;
    }

    private final TestGroupRepository testGroupRepository;
    private final MetricsConfiguration metricsConfiguration;

    public void createTest(final CreateTestRequest request) {
        final TestGroup activeTestGroup = getActiveTestGroup();
        if (activeTestGroup != null) {
            // TODO throw exception
            return;
        }
        final TestGroup testGroup = new TestGroup();
        testGroup.setName(request.getTestName());
        testGroup.setStatus(TestGroup.Status.ACTIVE);
        testGroup.setGroupUuid(UUID.randomUUID().toString());

        testGroupRepository.save(testGroup);
    }

    public TestGroup getActiveTestGroup() {
        return testGroupRepository.getActiveTestGroup();
    }

    @EventListener
    public void validateExistingTestGroup(final ContextRefreshedEvent event) {
        log.debug("On Context Refreshed.");
        final TestGroup activeTestGroup = getActiveTestGroup();
        if (activeTestGroup == null) {
            return;
        }
        log.debug("Setting test group id tag [{}]", activeTestGroup.getGroupUuid());
        metricsConfiguration.setTestGroupId(activeTestGroup.getGroupUuid());
    }
}
