package no.difi.vefa.validator.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.spi.FileSystemProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

public class StorageFileSystemProvider extends FileSystemProvider implements Closeable {

    private static Logger logger = LoggerFactory.getLogger(StorageFileSystemProvider.class);

    public static final String SCHEME = "validator";

    private static Map<String, StorageManager> validatorStorageMap = new HashMap<>();

    public void registerStorage(StorageManager storageManager) {
        validatorStorageMap.put(storageManager.getIdentifier(), storageManager);
    }

    public void removeStorage(StorageManager storageManager) {
        validatorStorageMap.remove(storageManager.getIdentifier());
    }

    static StorageFileSystemProvider getProvider() {
        try {
            for (FileSystemProvider provider : FileSystemProvider.installedProviders())
                if (provider.getScheme().equals(StorageFileSystemProvider.SCHEME))
                    return (StorageFileSystemProvider) provider;

            ServiceLoader<FileSystemProvider> loader = ServiceLoader.load(
                    FileSystemProvider.class, StorageFileSystemProvider.class.getClassLoader());

            for (FileSystemProvider provider : loader)
                if (provider.getScheme().equals(StorageFileSystemProvider.SCHEME))
                    return (StorageFileSystemProvider) provider;
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        }

        throw new IllegalStateException("Unable to locate provider.");
    }

    @Override
    public String getScheme() {
        return SCHEME;
    }

    @Override
    public FileSystem newFileSystem(URI uri, Map<String, ?> env) throws IOException {
        return new StorageFileSystem(this);
    }

    @Override
    public FileSystem getFileSystem(URI uri) {
        return new StorageFileSystem(this);
    }

    @Override
    public Path getPath(URI uri) {
        logger.info("{}", uri);
        return null;
    }

    @Override
    public SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs) throws IOException {
        // validatorStorageMap.get()
        logger.info("{}", path);

        return new ByteArrayChannel(new byte[]{}, 0);
    }

    @Override
    public DirectoryStream<Path> newDirectoryStream(Path dir, DirectoryStream.Filter<? super Path> filter) throws IOException {
        return null;
    }

    @Override
    public void createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException {

    }

    @Override
    public void delete(Path path) throws IOException {

    }

    @Override
    public void copy(Path source, Path target, CopyOption... options) throws IOException {

    }

    @Override
    public void move(Path source, Path target, CopyOption... options) throws IOException {

    }

    @Override
    public boolean isSameFile(Path path, Path path2) throws IOException {
        return false;
    }

    @Override
    public boolean isHidden(Path path) throws IOException {
        return false;
    }

    @Override
    public FileStore getFileStore(Path path) throws IOException {
        return null;
    }

    @Override
    public void checkAccess(Path path, AccessMode... modes) throws IOException {

    }

    @Override
    public <V extends FileAttributeView> V getFileAttributeView(Path path, Class<V> type, LinkOption... options) {
        return null;
    }

    @Override
    public <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options) throws IOException {
        return null;
    }

    @Override
    public Map<String, Object> readAttributes(Path path, String attributes, LinkOption... options) throws IOException {
        return null;
    }

    @Override
    public void setAttribute(Path path, String attribute, Object value, LinkOption... options) throws IOException {

    }

    @Override
    public void close() throws IOException {

    }
}
