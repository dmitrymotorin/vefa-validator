package no.difi.vefa.validator.util;

import no.difi.xsd.vefa.validator._1.Configurations;
import no.difi.xsd.vefa.validator._2.DefinitionsType;

import javax.xml.bind.JAXBContext;
import java.io.InputStream;

public class DefinitionsParser {

    private static JAXBContext jaxbContext = JAXBHelper.context(Configurations.class, DefinitionsType.class);

    public static DefinitionsType parse(InputStream inputStream) {
        return null;
    }
}
