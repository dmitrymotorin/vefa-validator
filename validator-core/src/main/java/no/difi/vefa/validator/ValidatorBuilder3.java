package no.difi.vefa.validator;

import com.google.inject.Guice;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import no.difi.vefa.validator.api.Repository;
import no.difi.vefa.validator.repository.HttpRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidatorBuilder3 {

    private Config config;

    private List<String> packages = new ArrayList<>();

    private List<Repository> repositories = new ArrayList<>();

    @Deprecated
    public static ValidatorBuilder3 newValidator() {
        return newValidator("eu.peppol.postaward.ver20", "eu.peppol.sbdh", "no.difi.ehf.postaward",
                "no.difi.ehf.stylesheet", "org.oasis-open.ubl.ver20", "org.oasis-open.ubl.ver21");
    }

    public static ValidatorBuilder3 newValidator(String... packages) {
        return new ValidatorBuilder3()
                .addPackage(packages);
    }

    private ValidatorBuilder3() {
        Config config = ConfigFactory.load();
        this.config = config.withFallback(config.getConfig("defaults"));
    }

    public ValidatorBuilder3 config(Config config) {
        this.config = config.withFallback(this.config);

        return this;
    }

    public ValidatorBuilder3 addRepository(Repository... source) {
        this.repositories.addAll(Arrays.asList(source));

        return this;
    }

    public ValidatorBuilder3 addPackage(String... pkg) {
        this.packages.addAll(Arrays.asList(pkg));

        return this;
    }

    public Validator3 build() {
        if (repositories.size() == 0)
            repositories.add(new HttpRepository(URI.create("https://vefa.difi.no/validator/repo/")));

        return Guice.createInjector(new ValidatorModule(config, packages, repositories))
                .getInstance(Validator3.class);
    }
}
