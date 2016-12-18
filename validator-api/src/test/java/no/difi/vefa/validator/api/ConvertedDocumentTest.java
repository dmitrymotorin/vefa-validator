package no.difi.vefa.validator.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

public class ConvertedDocumentTest {

    @Test
    public void simple() {
        ConvertedDocument document = new ConvertedDocument(new byte[] {}, new byte[] {}, "identifier", null);

        Assert.assertNotNull(document.getInputStream());
        Assert.assertNotNull(document.getSource());
        Assert.assertEquals(document.getDeclaration(), "identifier");
        Assert.assertNull(document.getExpectation());
    }

}
