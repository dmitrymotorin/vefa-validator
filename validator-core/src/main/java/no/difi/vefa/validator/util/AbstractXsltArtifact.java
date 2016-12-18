package no.difi.vefa.validator.util;

import net.sf.saxon.s9api.*;
import no.difi.vefa.validator.lang.ArtifactException;
import no.difi.xsd.vefa.validator._2.ArtifactDefinitionType;

import javax.xml.transform.stream.StreamSource;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class AbstractXsltArtifact<T extends ArtifactDefinitionType> extends AbstractArtifact<T> implements Closeable {

    private static final Processor PROCESSOR = new Processor(false);

    private XsltExecutable xsltExecutable;
    private Path file;

    public AbstractXsltArtifact(T definition, Path folder) throws ArtifactException {
        super(definition, folder);

        file = folder.resolve(definition.getFile());

        try (InputStream inputStream = Files.newInputStream(file)) {
            XsltCompiler xsltCompiler = PROCESSOR.newXsltCompiler();
            xsltCompiler.setErrorListener(SaxonErrorListener.INSTANCE);
            xsltCompiler.setURIResolver(new PathURIResolver(file.getParent()));
            xsltExecutable = xsltCompiler.compile(new StreamSource(inputStream));
        } catch (IOException | SaxonApiException e) {
            throw new ArtifactException(e.getMessage(), e);
        }
    }

    protected XsltTransformer getTransformer() {
        XsltTransformer xsltTransformer = xsltExecutable.load();
        xsltTransformer.setURIResolver(new PathURIResolver(file.getParent()));

        return xsltTransformer;
    }

    @Override
    public void close() throws IOException {
        xsltExecutable = null;
        file = null;
    }
}
