package configuration;

import io.micrometer.core.instrument.Tag;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.UUID;

@Data
@Configuration
@ConfigurationProperties(prefix = "metrics")
public class MetricsConfiguration implements MeterRegistryCustomizer<PrometheusMeterRegistry> {

    private static final String INSTANCE_ID = "instance_id";
    private static final String TEST_GROUP_ID = "test_group_id";
    private static final String PROVIDER = "cloud_provider";

    private String testGroupId;
    private String provider;

    @Autowired
    public MetricsConfiguration(final PrometheusMeterRegistry prometheusMeterRegistry) {
        this.prometheusMeterRegistry = prometheusMeterRegistry;
    }

    private final PrometheusMeterRegistry prometheusMeterRegistry;

    @Override
    public void customize(PrometheusMeterRegistry registry) {
        registry.config().commonTags(Arrays.asList(Tag.of(INSTANCE_ID, generateInstanceId()),
                Tag.of(PROVIDER, provider), Tag.of(TEST_GROUP_ID, testGroupId)));
    }

    private String generateInstanceId() {
        return UUID.randomUUID().toString();
    }
}
