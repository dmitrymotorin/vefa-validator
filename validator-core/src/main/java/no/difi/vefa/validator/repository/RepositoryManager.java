package no.difi.vefa.validator.repository;

import com.typesafe.config.Config;
import no.difi.vefa.validator.api.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class RepositoryManager {

    private static Logger logger = LoggerFactory.getLogger(RepositoryManager.class);

    private List<RepositoryWrapper> repositories = new ArrayList<>();

    private ExecutorService executorService;

    @Inject
    public RepositoryManager(Config config, @Named("internal") ExecutorService executorService, List<Repository> repositories) {
        this.executorService = executorService;

        for (Repository repository : repositories)
            this.repositories.add(new RepositoryWrapper(repository));

        loadRepositories();
    }

    private void loadRepositories() {
        try {
            List<Future<Boolean>> futures = new ArrayList<>();

            for (RepositoryWrapper repositoryWrapper : repositories)
                futures.add(executorService.submit(repositoryWrapper));

            boolean updated = false;
            for (Future<Boolean> future : futures)
                if (future.get())
                    updated = true;

            if (updated)
                logger.info("Updated repository found.");
        } catch (InterruptedException | ExecutionException e) {
            logger.warn(e.getMessage(), e);
        }
    }

    public void getPackage(String name) {
        for (RepositoryWrapper repository : repositories) {
            if (repository.hasPackage(name)) {
                return;
            }
        }
    }
}
