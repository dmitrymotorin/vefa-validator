package no.difi.vefa.validator;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.typesafe.config.Config;
import no.difi.vefa.validator.util.AbstractArtifact;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

public class ArtefactCache extends CacheLoader<String, AbstractArtifact> {

    private ArtefactFactory artefactFactory;

    private LoadingCache<String, AbstractArtifact> cache;

    @Inject
    public ArtefactCache(ArtefactFactory artefactFactory, Config config) {
        this.artefactFactory = artefactFactory;

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
        return Futures.immediateFuture(load(key));
    }
}
