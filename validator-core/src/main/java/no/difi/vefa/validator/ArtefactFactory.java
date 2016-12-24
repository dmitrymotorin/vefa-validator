package no.difi.vefa.validator;

import com.typesafe.config.Config;
import no.difi.vefa.validator.api.artifact.Artifact;

import javax.inject.Inject;

public class ArtefactFactory {

    private Config config;

    @Inject
    public ArtefactFactory(Config config) {
        this.config = config;
    }

    public Artifact load(String identifier) {
        // TODO
        return null;
    }
}
