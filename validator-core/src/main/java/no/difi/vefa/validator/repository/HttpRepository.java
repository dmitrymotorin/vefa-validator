package no.difi.vefa.validator.repository;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;

public class HttpRepository extends AbstractRepository {

    public HttpRepository(URI uri) {
        super(uri);
    }

    @Override
    public InputStream get(String filename) throws IOException {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) uri.resolve(filename).toURL().openConnection();
            return httpURLConnection.getInputStream();
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
