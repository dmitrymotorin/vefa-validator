package no.difi.vefa.validator.storage;

import javax.inject.Inject;
import java.io.Closeable;
import java.io.IOException;
import java.util.*;

public class StorageManager implements Closeable {

    private String identifier = UUID.randomUUID().toString();

    private Map<String, StoragePackage> packages = new HashMap<>();

    private List<String> initialPackages;

    @Inject
    public StorageManager(List<String> packages) {
        StorageFileSystemProvider.getProvider().registerStorage(this);
        this.initialPackages = packages;
    }

    public String getIdentifier() {
        return identifier;
    }

    public StoragePackage getPackage(String key) {
        return packages.get(key);
    }

    @Override
    public void close() throws IOException {
        StorageFileSystemProvider.getProvider().removeStorage(this);

        for (StoragePackage storagePackage : packages.values())
            storagePackage.close();

        packages.clear();
        packages = null;
    }
}
