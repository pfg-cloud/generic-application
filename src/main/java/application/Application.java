package application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"application", "controllers", "service", "config", "usecases", "configuration"})
@EnableJpaRepositories(basePackages = {"repositories"})
@EntityScan(basePackages = {"model.entity"})
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        final String provider = System.getenv("PROVIDER");
        final String testGroupId = System.getProperty("TEST_GROUP_ID");

        log.info("Environment Variables: Provider = {}, Test Group Id = {}", provider, testGroupId);
    }
}
