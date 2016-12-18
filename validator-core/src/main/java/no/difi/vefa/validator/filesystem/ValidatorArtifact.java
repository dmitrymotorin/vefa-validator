package no.difi.vefa.validator.filesystem;

import no.difi.asic.AsicReader;
import no.difi.asic.AsicReaderFactory;
import no.difi.vefa.validator.util.DefinitionsParser;
import no.difi.xsd.vefa.validator._2.DefinitionsType;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ValidatorArtifact implements Closeable {

    private final static Pattern PATTERN_CONFIG = Pattern.compile("^config\\-.+\\.xml");

    private AsicReaderFactory asicReaderFactory = AsicReaderFactory.newFactory();

    private String identifier;
    private long timestamp;

    private Map<String, byte[]> files = new HashMap<>();
    private DefinitionsType definitions;

    public ValidatorArtifact(InputStream inputStream) throws IOException {
        AsicReader asicReader = asicReaderFactory.open(inputStream);

        String filenameConfig = null;

        String filename;
        while ((filename = asicReader.getNextFile()) != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            asicReader.writeFile(byteArrayOutputStream);

            files.put(filename, byteArrayOutputStream.toByteArray());

            if (PATTERN_CONFIG.matcher(filename).matches())
                filenameConfig = filename;
        }

        asicReader.close();

        this.definitions = DefinitionsParser.parse(read(filenameConfig));

        this.identifier = definitions.getIdentifier();
        this.timestamp = definitions.getTimestamp();
    }

    public InputStream read(String path) {
        return new ByteArrayInputStream(files.get(path));
    }

    public DefinitionsType getDefinitions() {
        return definitions;
    }

    @Override
    public String toString() {
        return String.format("ValidatorArtifact[ %s / %s / %s files ]", identifier, timestamp, files.size());
    }

    @Override
    public void close() throws IOException {
        files.clear();
    }
}
