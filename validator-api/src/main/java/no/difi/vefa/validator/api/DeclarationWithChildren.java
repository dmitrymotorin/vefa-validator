package no.difi.vefa.validator.api;

import java.io.InputStream;

@Deprecated
public interface DeclarationWithChildren extends Declaration {
    Iterable<InputStream> children(InputStream inputStream) throws ValidatorException;
}
