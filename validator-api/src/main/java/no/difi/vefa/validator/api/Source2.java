package no.difi.vefa.validator.api;

import java.io.IOException;
import java.io.InputStream;

public interface Source2 {

    InputStream get(String filename) throws IOException;

}
