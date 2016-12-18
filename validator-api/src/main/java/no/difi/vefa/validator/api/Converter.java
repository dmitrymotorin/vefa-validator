package no.difi.vefa.validator.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Converter {

    void convert(InputStream inputStream, OutputStream outputStream) throws IOException;

}
