package no.difi.vefa.validator.api;

import no.difi.vefa.validator.lang.ValidatorException;

@Deprecated
public interface Trigger {

    void check(Document document, Section section) throws ValidatorException;
}
