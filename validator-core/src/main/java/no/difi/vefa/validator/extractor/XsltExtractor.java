package no.difi.vefa.validator.extractor;

import no.difi.vefa.validator.api.Extractor;
import no.difi.vefa.validator.lang.ArtifactException;
import no.difi.vefa.validator.util.AbstractXsltArtifact;
import no.difi.xsd.vefa.validator._2.ExtractorType;

import java.nio.file.Path;

public class XsltExtractor extends AbstractXsltArtifact<ExtractorType> implements Extractor {

    public XsltExtractor(ExtractorType definition, Path folder) throws ArtifactException {
        super(definition, folder);
    }
}
