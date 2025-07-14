package com.asench.slovo.repository;

import com.asench.slovo.container.MyTestContainers;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
public class RepositoryTestBase implements MyTestContainers {

}
