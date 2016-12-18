package no.difi.vefa.validator;

import no.difi.xsd.vefa.validator._2.ArtifactDefinitionType;
import no.difi.xsd.vefa.validator._2.DocumentTypeType;

import java.util.HashMap;
import java.util.Map;

public class DefinitionRepository {

    private Map<String, ArtifactDefinitionType> artifacts = new HashMap<>();
    private Map<String, DocumentTypeType> documentTypes = new HashMap<>();
    private Map<String, String> declarations = new HashMap<>();

}
