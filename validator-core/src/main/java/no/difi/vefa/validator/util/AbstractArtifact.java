package no.difi.vefa.validator.util;

import no.difi.xsd.vefa.validator._2.ArtifactDefinitionType;

import java.nio.file.Path;

public abstract class AbstractArtifact<T extends ArtifactDefinitionType> {

    protected final T definition;
    protected final Path folder;

    public AbstractArtifact(T definition, Path folder) {
        this.definition = definition;
        this.folder = folder;
    }

    public T getDefinition() {
        return definition;
    }
}
