package no.difi.vefa.validator;

import com.google.common.io.ByteStreams;
import com.google.inject.Injector;
import no.difi.vefa.validator.repository.RepositoryManager;
import no.difi.vefa.validator.util.DeclarationDetector;
import no.difi.vefa.validator.util.DeclarationIdentifier;

import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;

public class Validator3 {

    private Injector injector;

    private DeclarationDetector declarationDetector;

    @Inject
    Validator3(Injector injector) {
        this.injector = injector;
        this.declarationDetector = injector.getInstance(DeclarationDetector.class);

        injector.getInstance(RepositoryManager.class);
    }

    public void validate(InputStream inputStream) throws IOException {
        byte[] detectionBytes = new byte[10 * 1024];
        ByteStreams.readFully(inputStream, detectionBytes);

        DeclarationIdentifier declarationIdentifier = declarationDetector.detect(detectionBytes);

        // TODO Converter?

        byte[] bytes = ByteStreams.toByteArray(new SequenceInputStream(new ByteArrayInputStream(detectionBytes), inputStream));
        ByteStreams.copy(new ByteArrayInputStream(bytes), System.out);
    }
}
