package no.difi.vefa.validator.source;

import no.difi.vefa.validator.api.Source2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CachedSource2 implements Source2 {

    private Source2 source;
    private Path cachePath;

    public static Source2 of(Source2 source, Path cachePath) {
        return new CachedSource2(source, cachePath);
    }

    public CachedSource2(Source2 source, Path cachePath) {
        this.source = source;
        this.cachePath = cachePath;
    }

    @Override
    public InputStream get(String filename) throws IOException {
        Path cachedFile = cachePath.resolve(filename);

        if (Files.exists(cachedFile))
            return Files.newInputStream(cachedFile);

        // TODO Perform caching
        return source.get(filename);
    }
}
