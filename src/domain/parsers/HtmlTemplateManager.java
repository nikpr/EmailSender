package domain.Parsers;

import domain.Model.EmailBody;
import java.io.File;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 *
 * @author Nik
 */
public class HtmlTemplateManager {

    private XMLEncoder encoder;
    private XMLDecoder decoder;

    public HtmlTemplateManager() {

    }

    public EmailBody load(File file) throws Exception {
        try (InputStream in = new FileInputStream(file)) {
            decoder = new XMLDecoder(in);
            EmailBody emailBody = (EmailBody) decoder.readObject();
            return emailBody;
        } finally {
            if (decoder != null) {
                decoder.close();
            }
        }
    }

    public void save(File file, EmailBody emailBody) throws Exception {

        try (OutputStream out = new FileOutputStream(file)) {
            encoder = new XMLEncoder(out);
            encoder.writeObject(emailBody);
        } finally {
            if (encoder != null) {
                encoder.close();
            }
        }
    }

    public String extractHtmlTemplate(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        StringBuilder parsedTemplate = new StringBuilder();
        while (scan.hasNext()) {
            String line = scan.nextLine();
            parsedTemplate.append(line);
        }
        return parsedTemplate.toString();
    }
}
