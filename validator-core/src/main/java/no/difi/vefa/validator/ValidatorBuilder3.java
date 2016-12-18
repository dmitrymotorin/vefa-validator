package no.difi.vefa.validator;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ValidatorBuilder3 {

    private Config config;

    public static ValidatorBuilder3 newValidator() {
        return new ValidatorBuilder3();
    }

    private ValidatorBuilder3() {
        Config config = ConfigFactory.load();
        this. config = config.withFallback(config.getConfig("defaults"));
    }

    public ValidatorBuilder3 config(Config config) {
        this.config = config.withFallback(this.config);

        return this;
    }

    public Validator3 build() {
        return new Validator3(config);
    }
}
