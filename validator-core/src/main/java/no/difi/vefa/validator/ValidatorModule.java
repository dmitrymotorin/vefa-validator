package no.difi.vefa.validator;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.typesafe.config.Config;
import no.difi.vefa.validator.api.Repository;
import no.difi.vefa.validator.repository.RepositoryManager;
import no.difi.vefa.validator.storage.StorageManager;
import no.difi.vefa.validator.util.DeclarationDetector;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("unused")
class ValidatorModule extends AbstractModule {

    private Config config;

    private List<String> packages;

    private List<Repository> repositories;

    public ValidatorModule(Config config, List<String> packages, List<Repository> repositories) {
        this.config = config;
        this.packages = packages;
        this.repositories = repositories;
    }

    @Override
    protected void configure() {
        bind(RepositoryManager.class)
                .in(Singleton.class);

        bind(ArtefactConfiguration.class)
                .in(Singleton.class);

        bind(ArtefactFactory.class)
                .in(Singleton.class);
        bind(ArtefactCache.class)
                .in(Singleton.class);

        bind(DeclarationDetector.class)
                .in(Singleton.class);

        bind(StorageManager.class)
                .in(Singleton.class);

        // Execution services
        bind(ExecutorService.class)
                .annotatedWith(Names.named("artifact"))
                .toInstance(Executors.newFixedThreadPool(100));
        bind(ExecutorService.class)
                .annotatedWith(Names.named("validator"))
                .toInstance(Executors.newFixedThreadPool(10));
        bind(ExecutorService.class)
                .annotatedWith(Names.named("internal"))
                .toInstance(Executors.newFixedThreadPool(5));
    }

    @Provides
    FileSystem fileSystemProvider(StorageManager storageManager) {
        return FileSystems.getFileSystem(URI.create(String.format("validator://%s/", storageManager.getIdentifier())));
    }

    @Provides
    Config configProvider() {
        return config;
    }

    @Provides
    List<String> packagesProvider() {
        return packages;
    }

    @Provides
    List<Repository> repositoriesProvider() {
        return repositories;
    }
}
