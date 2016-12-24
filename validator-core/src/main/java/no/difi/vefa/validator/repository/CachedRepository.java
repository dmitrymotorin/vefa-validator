package no.difi.vefa.validator.repository;

import no.difi.vefa.validator.api.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CachedRepository implements Repository {

    private static Logger logger = LoggerFactory.getLogger(CachedRepository.class);

    private Repository repository;
    private Path cachePath;

    public CachedRepository(Repository repository) {
        this(repository, new File(System.getProperty("java.io.tmpdir")).toPath().resolve("vefa-validator-tmp"));
    }

    public CachedRepository(Repository repository, Path cachePath) {
        this.repository = repository;
        this.cachePath = cachePath;

        logger.info("Cache path: {}", cachePath);
    }

    @Override
    public InputStream get(String filename) throws IOException {
        Path cachedFile = cachePath.resolve(filename);

        if (Files.exists(cachedFile))
            return Files.newInputStream(cachedFile);

        // TODO Perform caching
        return repository.get(filename);
    }
}
