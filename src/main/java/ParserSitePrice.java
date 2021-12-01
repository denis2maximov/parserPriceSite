import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParserSitePrice {
    public static String pageParseDiapus() throws IOException {
        String url = "https://diapuls.ru/product/test-poloski-ime-dc-50/";
        Document page = Jsoup.parse(new URL(url), 3000);
        Element price = page.select("div.price").first();
        return price.text();
    }

    public static String pageParseTestpoloska() throws IOException {
        String url = "https://www.test-poloska.ru/catalog/teststripes/imedc50.html";
        Document page = Jsoup.parse(new URL(url), 3000);
        Element price = page.select("tbody").get(10);
        Elements priceTS = price.getElementsByAttribute("nowrap");
        return Stream.of(priceTS.text().split(" ")).toList().get(1);
    }

    public static String pageParseMedMag() throws IOException {
                String url = "https://www.medmag.ru/index.php?productID=792&from=ya";
                Document page = Jsoup.parse(new URL(url), 3000);
                Element price = page.select("font.price").first();
                return price.text();
    }

    public static void main(String[] args) throws IOException {
        String pageDiapuls = pageParseDiapus();
        String pageMedMag = pageParseMedMag();
        String pageTP = pageParseTestpoloska();
       System.out.println("Цена полосок ime-dc на сайте diapuls.ru: "
               + pageDiapuls
               + System.lineSeparator()
               + "Цена полосок ime-dc на сайте medmag.ru:  "
               + pageMedMag
               + System.lineSeparator()
               + "Цена полосок ime-dc на сайте test-poloska.ru:  "
               + pageTP);
    }
}


