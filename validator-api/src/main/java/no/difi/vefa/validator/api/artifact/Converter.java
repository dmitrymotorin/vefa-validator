package no.difi.vefa.validator.api.artifact;

import no.difi.xsd.vefa.validator._2.ConverterType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Converter extends Artifact<ConverterType> {

    void convert(InputStream inputStream, OutputStream outputStream) throws IOException;

}
