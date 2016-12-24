package no.difi.vefa.validator;

import no.difi.xsd.vefa.validator._2.DeclarationType;
import no.difi.xsd.vefa.validator._2.DocumentTypeType;

import java.util.HashMap;
import java.util.Map;

public class ArtefactConfiguration {

    private Map<String, DocumentTypeType> documentTypes = new HashMap<>();

    private Map<String, DeclarationType> declarations = new HashMap<>();

}
