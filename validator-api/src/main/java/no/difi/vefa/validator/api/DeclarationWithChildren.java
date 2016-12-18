package no.difi.vefa.validator.api;

import no.difi.vefa.validator.lang.ValidatorException;

import java.io.InputStream;

@Deprecated
public interface DeclarationWithChildren extends Declaration {
    Iterable<InputStream> children(InputStream inputStream) throws ValidatorException;
}
