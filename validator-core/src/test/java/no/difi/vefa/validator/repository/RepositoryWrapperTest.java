package no.difi.vefa.validator.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.net.URI;

public class RepositoryWrapperTest {

    private static Logger logger = LoggerFactory.getLogger(RepositoryWrapperTest.class);

    @Test
    public void simple() {
        RepositoryWrapper repositoryWrapper = new RepositoryWrapper(new HttpRepository(URI.create("https://test-vefa.difi.no/validator/repo/")));
        logger.info("{}", repositoryWrapper.getPackageNames());
    }
}
