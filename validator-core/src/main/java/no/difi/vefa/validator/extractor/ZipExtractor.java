package no.difi.vefa.validator.extractor;

import no.difi.vefa.validator.api.Extractor;
import no.difi.vefa.validator.util.AbstractArtifact;
import no.difi.xsd.vefa.validator._2.ExtractorType;

import java.nio.file.Path;

public class ZipExtractor extends AbstractArtifact<ExtractorType> implements Extractor {

    public ZipExtractor(ExtractorType definition, Path folder) {
        super(definition, folder);
    }
}
