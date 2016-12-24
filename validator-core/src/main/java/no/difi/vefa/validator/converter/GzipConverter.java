package no.difi.vefa.validator.converter;

import com.google.common.io.ByteStreams;
import no.difi.vefa.validator.api.artifact.Converter;
import no.difi.vefa.validator.util.AbstractArtifact;
import no.difi.xsd.vefa.validator._2.ConverterType;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.zip.GZIPInputStream;

public class GzipConverter extends AbstractArtifact<ConverterType> implements Converter {

    public GzipConverter(ConverterType definition, Path folder) {
        super(definition, folder);
    }

    @Override
    public void convert(InputStream inputStream, OutputStream outputStream) throws IOException {
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream)) {
            ByteStreams.copy(gzipInputStream, outputStream);
        }
    }
}
