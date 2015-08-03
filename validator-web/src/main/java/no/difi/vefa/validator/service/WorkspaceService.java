package no.difi.vefa.validator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import no.difi.vefa.validator.Validation;
import no.difi.xsd.vefa.validator._1.Report;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

@Service
public class WorkspaceService {

    private static Logger logger = LoggerFactory.getLogger(WorkspaceService.class);

    @Value("${dir.workspace}")
    private String dirWorkspace;

    private Marshaller reportMarshaller;
    private Unmarshaller reportUnmarshaller;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void postContruct() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Report.class);
            reportMarshaller = jaxbContext.createMarshaller();
            reportUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected File getFolder(String identifier) {
        return new File(dirWorkspace, identifier);
    }

    @SuppressWarnings("all")
    public String saveValidation(Validation validation) throws Exception {
        String identifier = UUID.randomUUID().toString();
        File folder = getFolder(identifier);
        folder.mkdirs();

        FileOutputStream fileOutputStream = new FileOutputStream(new File(folder, "source.xml.gz"));
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
        IOUtils.copy(validation.getDocument().getInputStream(), gzipOutputStream);
        gzipOutputStream.close();
        fileOutputStream.close();

        fileOutputStream = new FileOutputStream(new File(folder, "report.xml.gz"));
        gzipOutputStream = new GZIPOutputStream(fileOutputStream);
        reportMarshaller.marshal(validation.getReport(), gzipOutputStream);
        gzipOutputStream.close();
        fileOutputStream.close();

        fileOutputStream = new FileOutputStream(new File(folder, "report.json.gz"));
        gzipOutputStream = new GZIPOutputStream(fileOutputStream);
        objectMapper.writeValue(gzipOutputStream, validation.getReport());
        gzipOutputStream.close();
        fileOutputStream.close();

        try {
            fileOutputStream = new FileOutputStream(new File(folder, "view.html"));
            validation.present(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            // No action
        }

        return identifier;
    }

    public boolean exists(String identifier) {
        return getFolder(identifier).isDirectory();
    }

    public Report getReport(String identifier) throws Exception {
        InputStream inputStream = new FileInputStream(getReportXml(identifier));
        GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
        Report report = (Report) reportUnmarshaller.unmarshal(gzipInputStream);
        gzipInputStream.close();
        inputStream.close();

        return report;
    }

    public File getReportJson(String identifier) {
        return new File(getFolder(identifier), "report.json.gz");
    }

    public File getReportXml(String identifier) {
        return new File(getFolder(identifier), "report.xml.gz");
    }

    public File getSource(String identifier) {
        return new File(getFolder(identifier), "source.xml.gz");
    }

    public File getView(String identifier) {
        return new File(getFolder(identifier), "view.html");
    }
}