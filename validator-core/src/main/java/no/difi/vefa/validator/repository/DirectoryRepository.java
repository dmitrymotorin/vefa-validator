package no.difi.vefa.validator.repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class DirectoryRepository extends AbstractRepository {

    public DirectoryRepository(URI uri) {
        super(uri);
    }

    @Override
    public InputStream get(String filename) throws IOException {

        return null;
    }
}
