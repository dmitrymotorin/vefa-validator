package no.difi.vefa.validator.storage;

import org.testng.annotations.Test;

import java.io.InputStream;

public class StorageManagerPackageTest {

    @Test
    public void simple() throws Exception {
        StoragePackage storagePackage;

        try (InputStream inputStream = getClass().getResourceAsStream("/rules/ehf-79b2def888aba2a33ccc7606c4b39f5448c3585e.asice")) {
            storagePackage = new StoragePackage(inputStream);
        }

        System.out.println(storagePackage);
    }
}
