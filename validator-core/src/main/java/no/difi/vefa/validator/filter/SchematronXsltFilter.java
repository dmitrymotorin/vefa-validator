package no.difi.vefa.validator.filter;

import no.difi.vefa.validator.lang.ArtifactException;
import no.difi.xsd.vefa.validator._2.FilterType;

import java.nio.file.Path;

public class SchematronXsltFilter extends SchematronFilter {

    public SchematronXsltFilter(FilterType definition, Path folder) throws ArtifactException {
        super(definition, folder);
    }
}
