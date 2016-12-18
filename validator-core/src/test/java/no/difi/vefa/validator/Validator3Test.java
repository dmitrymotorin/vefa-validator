package no.difi.vefa.validator;

import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;

public class Validator3Test {

    @Test
    public void simple() throws IOException {
        Validator3 validator3 = ValidatorBuilder3.newValidator().build();

        try (InputStream inputStream = getClass().getResourceAsStream("/documents/ehf-invoice-2.0.xml")) {
            validator3.validate(inputStream);
        }
    }
}
