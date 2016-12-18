package no.difi.vefa.validator;

import com.google.common.io.ByteStreams;
import com.typesafe.config.Config;
import no.difi.vefa.validator.api.Declaration;
import no.difi.vefa.validator.util.DeclarationDetector;
import no.difi.vefa.validator.util.DeclarationIdentifier;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Validator3 implements Closeable {

    private Config config;

    private DeclarationDetector declarationDetector;

    public Validator3(Config config) {
        this.config = config;
        this.declarationDetector = new DeclarationDetector(config);
    }

    public void validate(InputStream inputStream) throws IOException {
        byte[] bytes = ByteStreams.toByteArray(inputStream);
        byte[] detectionBytes = Arrays.copyOfRange(bytes, 0, Math.min(10000, bytes.length));

        DeclarationIdentifier declarationIdentifier = declarationDetector.detect(detectionBytes);

        System.out.println(declarationIdentifier);
    }

    @Override
    public void close() throws IOException {
        this.config = null;
    }
}
