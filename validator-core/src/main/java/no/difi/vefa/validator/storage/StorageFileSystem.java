package no.difi.vefa.validator.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.Set;

public class StorageFileSystem extends FileSystem {

    private static Logger logger = LoggerFactory.getLogger(StorageFileSystem.class);

    private StorageFileSystemProvider storageFileSystemProvider;

    public StorageFileSystem(StorageFileSystemProvider storageFileSystemProvider) {
        this.storageFileSystemProvider = storageFileSystemProvider;
    }

    @Override
    public FileSystemProvider provider() {
        return storageFileSystemProvider;
    }

    @Override
    public boolean isOpen() {
        return true;
    }

    @Override
    public boolean isReadOnly() {
        return true;
    }

    @Override
    public String getSeparator() {
        return "/";
    }

    @Override
    public Iterable<Path> getRootDirectories() {
        return null;
    }

    @Override
    public Iterable<FileStore> getFileStores() {
        return null;
    }

    @Override
    public Set<String> supportedFileAttributeViews() {
        return null;
    }

    @Override
    public Path getPath(String first, String... more) {
        logger.info("{} {}", first, more);
        return new StoragePath(this);
    }

    @Override
    public PathMatcher getPathMatcher(String syntaxAndPattern) {
        return null;
    }

    @Override
    public UserPrincipalLookupService getUserPrincipalLookupService() {
        return null;
    }

    @Override
    public WatchService newWatchService() throws IOException {
        return null;
    }

    @Override
    public void close() throws IOException {
        storageFileSystemProvider = null;
    }
}
