package no.difi.vefa.validator.filter;

import no.difi.vefa.validator.api.Filter;
import no.difi.vefa.validator.util.AbstractArtifact;
import no.difi.xsd.vefa.validator._2.FilterType;

import java.nio.file.Path;

public class XsdFilter extends AbstractArtifact<FilterType> implements Filter {

    public XsdFilter(FilterType definition, Path folder) {
        super(definition, folder);
    }
}
