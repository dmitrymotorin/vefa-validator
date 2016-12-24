package no.difi.vefa.validator.api.artifact;

import no.difi.xsd.vefa.validator._2.AbstractArtifactType;

public interface Artifact<T extends AbstractArtifactType> {

    T getDefinition();
}
