package no.difi.vefa.validator.converter;

import no.difi.vefa.validator.api.artifact.Converter;
import no.difi.vefa.validator.lang.ArtifactException;
import no.difi.vefa.validator.util.AbstractXsltArtifact;
import no.difi.xsd.vefa.validator._2.ConverterType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

public class XsltConverter extends AbstractXsltArtifact<ConverterType> implements Converter {

    public XsltConverter(ConverterType definition, Path folder) throws ArtifactException {
        super(definition, folder);
    }

    @Override
    public void convert(InputStream inputStream, OutputStream outputStream) throws IOException {
        // TODO
    }
}
