package no.difi.vefa.validator.util;

import net.sf.saxon.s9api.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class XsltFilterFactory implements Closeable {

    private Map<String, XsltExecutable> executableMap = new HashMap<>();

    private XsltCompiler xsltCompiler;
    private JAXBContext jaxbContext;

    public static XsltFilterFactory newFactory(Class... classes) {
        return newFactory(new Processor(false), classes);
    }

    public static XsltFilterFactory newFactory(Processor processor, Class... classes) {
        return new XsltFilterFactory(processor, classes);
    }

    public XsltFilterFactory(Processor processor, Class... classes) {
        this.xsltCompiler = processor.newXsltCompiler();

        if (classes.length > 0)
            jaxbContext = JAXBHelper.context(classes);
    }

    public void load(String identifier, InputStream inputStream) throws SaxonApiException {
        executableMap.put(identifier, xsltCompiler.compile(new StreamSource(inputStream)));
    }

    public InputStream filter(String identifier, InputStream inputStream) throws SaxonApiException {
        XdmDestination destination = new XdmDestination();

        XsltTransformer xsltTransformer = executableMap.get(identifier).load();
        xsltTransformer.setSource(new StreamSource(inputStream));
        xsltTransformer.setDestination(destination);
        xsltTransformer.transform();
        xsltTransformer.close();

        byte[] result = destination.getXdmNode().toString().getBytes(StandardCharsets.UTF_8);
        return new ByteArrayInputStream(result);
    }

    public <T> T filter(String identifier, InputStream inputStream, Class<T> cls) throws SaxonApiException, JAXBException {
        return jaxbContext.createUnmarshaller().unmarshal(new StreamSource(filter(identifier, inputStream)), cls).getValue();
    }

    @Override
    public void close() throws IOException {
        xsltCompiler = null;
        executableMap.clear();
    }
}
