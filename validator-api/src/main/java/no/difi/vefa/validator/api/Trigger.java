package no.difi.vefa.validator.api;

@Deprecated
public interface Trigger {

    void check(Document document, Section section) throws ValidatorException;
}
