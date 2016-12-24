package no.difi.vefa.validator.storage;

import no.difi.asic.AsicReader;
import no.difi.asic.AsicReaderFactory;
import no.difi.vefa.validator.util.DefinitionsParser;
import no.difi.xsd.vefa.validator._2.DefinitionsType;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class StoragePackage implements Closeable {

    private final static Pattern PATTERN_CONFIG = Pattern.compile("^config\\-.+\\.xml");

    private final static AsicReaderFactory ASIC_READER_FACTORY = AsicReaderFactory.newFactory();

    private final String identifier;

    private final long timestamp;

    private Map<String, byte[]> files = new HashMap<>();

    private DefinitionsType definitions;

    public StoragePackage(InputStream inputStream) throws IOException {
        AsicReader asicReader = ASIC_READER_FACTORY.open(inputStream);

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
        return String.format("StoragePackage[ %s / %s / %s files ]", identifier, timestamp, files.size());
    }

    @Override
    public void close() throws IOException {
        files.clear();
        files = null;

        definitions = null;
    }
}
