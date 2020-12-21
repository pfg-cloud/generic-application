package configuration;

import io.micrometer.core.instrument.Tag;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MetricsConfiguration implements MeterRegistryCustomizer<PrometheusMeterRegistry> {

    private static final String INSTANCE_ID = "instance_id";
    private static final String PROVIDER = "cloud_provider";

    @Override
    public void customize(PrometheusMeterRegistry registry) {
        registry.config().commonTags(Arrays.asList(Tag.of(INSTANCE_ID, "instance 1"),
                Tag.of(PROVIDER, "aws")));
    }
}
