package repositories;

import model.entity.TestGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestGroupRepository extends JpaRepository<TestGroup, Long> {
}
