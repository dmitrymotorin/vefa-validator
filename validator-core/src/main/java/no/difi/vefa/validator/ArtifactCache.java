package no.difi.vefa.validator;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.typesafe.config.Config;
import no.difi.vefa.validator.util.AbstractArtifact;

import java.util.concurrent.TimeUnit;

public class ArtifactCache extends CacheLoader<String, AbstractArtifact> {

    private DefinitionRepository definitionRepository;
    private Config config;

    private LoadingCache<String, AbstractArtifact> cache;

    public ArtifactCache(DefinitionRepository definitionRepository, Config config) {
        this.definitionRepository = definitionRepository;
        this.config = config;

        this.cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(5, TimeUnit.MINUTES)
                .build(this);
        // .maximumSize(this.properties.getInteger("pools.checker.size"))
        // .expireAfterAccess(this.properties.getInteger("pools.checker.expire"), TimeUnit.MINUTES)
    }

    @Override
    public AbstractArtifact load(String key) throws Exception {
        return null;
    }

    @Override
    public ListenableFuture<AbstractArtifact> reload(String key, AbstractArtifact oldValue) throws Exception {
        return super.reload(key, oldValue);
    }
}
