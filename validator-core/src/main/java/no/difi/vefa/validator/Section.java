package no.difi.vefa.validator;

import no.difi.xsd.vefa.validator._1.AssertionType;
import no.difi.xsd.vefa.validator._1.FlagType;
import no.difi.xsd.vefa.validator._1.SectionType;
import org.oclc.purl.dsdl.svrl.FailedAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Section extends SectionType {

    private static Logger logger = LoggerFactory.getLogger(Section.class);

    private Document document;
    private Configuration configuration;

    public Section(Document document, Configuration configuration) {
        this.document = document;
        this.configuration = configuration;

        this.setFlag(FlagType.OK);
    }

    public AssertionType add(String identifier, String text, FlagType flagType) {
        AssertionType AssertionType = new AssertionType();
        AssertionType.setIdentifier(identifier);
        AssertionType.setText(text);
        AssertionType.setFlag(flagType);

        getAssertion().add(AssertionType);
        return AssertionType;
    }

    public void add(FailedAssert failedAssert) {
        AssertionType assertionType = new AssertionType();

        String text = failedAssert.getText().replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "");
        if (text.startsWith("[") && text.contains("]-")) {
            assertionType.setIdentifier(text.substring(1, text.indexOf("]-")).trim());
            text = text.substring(text.indexOf("]-") + 2).trim();
        } else {
            assertionType.setIdentifier("UNKNOWN");
        }

        assertionType.setText(text);
        assertionType.setLocation(failedAssert.getLocation());
        assertionType.setTest(failedAssert.getTest());

        switch (failedAssert.getFlag()) {
            case "fatal":
                assertionType.setFlag(FlagType.ERROR);
                break;
            case "warning":
                assertionType.setFlag(FlagType.WARNING);
                break;
            default:
                logger.warn("Unknown: " + failedAssert.getFlag());
                break;
        }

        configuration.filterFlag(assertionType);
        document.getExpectation().filterFlag(assertionType);

        if (assertionType.getFlag() != null) {
            if (assertionType.getFlag().compareTo(getFlag()) > 0)
                setFlag(assertionType.getFlag());

            this.getAssertion().add(assertionType);
        }
    }
}