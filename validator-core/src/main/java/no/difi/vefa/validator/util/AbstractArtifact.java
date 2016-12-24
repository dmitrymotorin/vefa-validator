package no.difi.vefa.validator.util;

import no.difi.vefa.validator.api.artifact.Artifact;
import no.difi.xsd.vefa.validator._2.ArtifactDefinitionType;

import java.nio.file.Path;

public abstract class AbstractArtifact<T extends ArtifactDefinitionType> implements Artifact<T> {

    protected final T definition;
    protected final Path folder;

    public AbstractArtifact(T definition, Path folder) {
        this.definition = definition;
        this.folder = folder;
    }

    @Override
    public T getDefinition() {
        return definition;
    }
}
