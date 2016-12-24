package no.difi.vefa.validator.repository;

import no.difi.vefa.validator.api.Repository;

import java.net.URI;

abstract class AbstractRepository implements Repository {

    protected URI uri;

    public AbstractRepository(URI uri) {
        this.uri = uri;
    }
}
