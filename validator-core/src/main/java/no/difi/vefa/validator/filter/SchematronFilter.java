package no.difi.vefa.validator.filter;

import no.difi.vefa.validator.api.Filter;
import no.difi.vefa.validator.lang.ArtifactException;
import no.difi.vefa.validator.util.AbstractXsltArtifact;
import no.difi.xsd.vefa.validator._2.FilterType;

import java.nio.file.Path;

public class SchematronFilter extends AbstractXsltArtifact<FilterType> implements Filter {

    public SchematronFilter(FilterType definition, Path folder) throws ArtifactException {
        super(definition, folder);
    }
}
