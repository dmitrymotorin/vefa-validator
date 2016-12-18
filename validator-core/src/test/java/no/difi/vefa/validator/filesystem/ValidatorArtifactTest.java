package no.difi.vefa.validator.filesystem;

import org.testng.annotations.Test;

import java.io.InputStream;

public class ValidatorArtifactTest {

    @Test
    public void simple() throws Exception {
        ValidatorArtifact validatorArtifact;

        try (InputStream inputStream = getClass().getResourceAsStream("/rules/ehf-79b2def888aba2a33ccc7606c4b39f5448c3585e.asice")) {
            validatorArtifact = new ValidatorArtifact(inputStream);
        }

        System.out.println(validatorArtifact);
    }
}
