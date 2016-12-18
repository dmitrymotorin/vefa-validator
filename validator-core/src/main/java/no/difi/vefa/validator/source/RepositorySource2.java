package no.difi.vefa.validator.source;

import no.difi.vefa.validator.api.Source2;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;

public class RepositorySource2 implements Source2 {

    private URI uri;

    public static Source2 of(URI uri) {
        return new RepositorySource2(uri);
    }

    public static Source2 of(String uri) {
        return of(URI.create(uri));
    }

    public RepositorySource2(URI uri) {
        this.uri = uri;
    }

    @Override
    public InputStream get(String filename) throws IOException {
        try {
            return uri.resolve(filename).toURL().openStream();
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
