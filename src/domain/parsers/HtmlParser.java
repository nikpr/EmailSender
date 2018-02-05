package domain.Parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 *
 * @author Nik
 */
public class HtmlParser {

    public String alignText(String htmlText){
    Document doc = Jsoup.parse(htmlText);
    StringBuilder builder = new StringBuilder();
        doc.body().children().forEach(element -> {
        builder.append(element.toString()).append("\n");
        });
    return builder.toString();
    }

}
