package no.difi.vefa.validator.declaration;

import no.difi.vefa.validator.api.Declaration;
import no.difi.vefa.validator.api.Expectation;
import no.difi.vefa.validator.api.ValidatorException;

import java.util.Arrays;

public class GzipDeclaration implements Declaration {

    private static final byte[] startsWith = new byte[]{(byte) 0x1f, (byte) 0x8b};

    @Override
    public boolean verify(byte[] content, String parent) throws ValidatorException {
        return Arrays.equals(startsWith, Arrays.copyOfRange(content, 0, 2));
    }

    @Override
    public String detect(byte[] content, String parent) throws ValidatorException {
        return "application/gzip";
    }

    @Override
    public Expectation expectations(byte[] content) throws ValidatorException {
        return null;
    }
}
