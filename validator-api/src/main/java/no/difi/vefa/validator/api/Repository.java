package no.difi.vefa.validator.api;

import java.io.IOException;
import java.io.InputStream;

public interface Repository {

    InputStream get(String filename) throws IOException;

}
