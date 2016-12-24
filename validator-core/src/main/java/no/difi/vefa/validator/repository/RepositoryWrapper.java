package no.difi.vefa.validator.repository;

import net.sf.saxon.s9api.SaxonApiException;
import no.difi.vefa.validator.api.Repository;
import no.difi.vefa.validator.util.XsltTransformerFactory;
import no.difi.xsd.vefa.validator.repository._2.PackageType;
import no.difi.xsd.vefa.validator.repository._2.RepositoryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class RepositoryWrapper implements Callable<Boolean> {

    private static Logger logger = LoggerFactory.getLogger(RepositoryWrapper.class);

    private static XsltTransformerFactory transformerFactory = XsltTransformerFactory.newFactory(RepositoryType.class);

    static {
        try {
            transformerFactory.load("v1-to-v2", RepositoryWrapper.class.getResourceAsStream("/repository-v1-v2.xsl"));
        } catch (SaxonApiException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private Repository repository;

    private long timestamp;

    private Map<String, PackageType> packageMap;

    public RepositoryWrapper(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean call() throws Exception {
        return loadManifest();
    }

    private boolean loadManifest() {
        try {
            RepositoryType repository = transformerFactory.transform("v1-to-v2", this.repository.get("artifacts.xml"), RepositoryType.class);

            if (timestamp < repository.getUpdated()) {
                timestamp = repository.getUpdated();

                Map<String, PackageType> artifactMap = new HashMap<>();
                for (PackageType pkg : repository.getPackage())
                    artifactMap.put(pkg.getName(), pkg);
                this.packageMap = artifactMap;

                return true;
            }
        } catch (IOException | SaxonApiException | JAXBException e) {
            logger.warn(e.getMessage(), e);
        }

        return false;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Set<String> getPackageNames() {
        return packageMap.keySet();
    }

    public PackageType getPackage(String name) {
        return packageMap.get(name);
    }

    public boolean hasPackage(String name) {
        return packageMap.containsKey(name);
    }

    public InputStream getPackageStream(String name) throws IOException {
        return repository.get(getPackage(name).getFilename());
    }
}
