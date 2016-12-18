package no.difi.vefa.validator.build.util;

import net.sf.saxon.s9api.SaxonApiException;
import no.difi.vefa.validator.util.XsltTransformerFactory;
import no.difi.xsd.vefa.validator._2.DefinitionsType;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

public class DefinitionsPreparer {

    private DefinitionsType definitions = new DefinitionsType();
    private XsltTransformerFactory xsltTransformerFactory = XsltTransformerFactory.newFactory(DefinitionsType.class);

    public static DefinitionsPreparer newInstance() throws SaxonApiException {
        return new DefinitionsPreparer();
    }

    private DefinitionsPreparer() throws SaxonApiException {
        xsltTransformerFactory.load("v1-to-v2", getClass().getResourceAsStream("/v1-to-v2.xsl"));
    }

    public DefinitionsPreparer load(InputStream inputStream) throws SaxonApiException, JAXBException {
        DefinitionsType definitionsType = xsltTransformerFactory.transform("v1-to-v2", inputStream, DefinitionsType.class);

        definitions.getConverterOrDocumentTypeOrExtractor().addAll(definitionsType.getConverterOrDocumentTypeOrExtractor());

        return this;
    }

}
