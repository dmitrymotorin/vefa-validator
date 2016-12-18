package no.difi.vefa.validator.stylesheet;

import no.difi.vefa.validator.api.Stylesheet;
import no.difi.vefa.validator.lang.ArtifactException;
import no.difi.vefa.validator.util.AbstractXsltArtifact;
import no.difi.xsd.vefa.validator._2.StylesheetType;

import java.nio.file.Path;

public class XsltStylesheet extends AbstractXsltArtifact<StylesheetType> implements Stylesheet {

    public XsltStylesheet(StylesheetType definition, Path folder) throws ArtifactException {
        super(definition, folder);
    }
}
