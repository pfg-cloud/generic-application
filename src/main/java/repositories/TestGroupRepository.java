package repositories;

import model.entity.TestGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TestGroupRepository extends JpaRepository<TestGroup, Long> {

    @Query(value = "SELECT * FROM test_group WHERE status = 'A'", nativeQuery = true)
    TestGroup getActiveTestGroup();

}
