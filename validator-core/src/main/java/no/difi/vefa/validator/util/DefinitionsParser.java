package no.difi.vefa.validator.util;

import net.sf.saxon.s9api.SaxonApiException;
import no.difi.xsd.vefa.validator._2.DefinitionsType;

import javax.xml.bind.JAXBException;
import java.io.InputStream;

public class DefinitionsParser {

    private static XsltTransformerFactory xsltTransformerFactory;

    static {
        try {
            xsltTransformerFactory = XsltTransformerFactory.newFactory(DefinitionsType.class);
            xsltTransformerFactory.load("v1-to-v2", DefinitionsParser.class.getResourceAsStream("/src/main/resources/config-v1-v2.xsl"));
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static DefinitionsType parse(InputStream inputStream) {
        try {
            return xsltTransformerFactory.transform("v1-to-v2", inputStream, DefinitionsType.class);
        } catch (SaxonApiException | JAXBException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
