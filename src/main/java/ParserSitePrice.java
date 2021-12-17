import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.jsoup.Jsoup.parse;

public class ParserSitePrice extends Application {
    public static String pageParseDiapus() throws IOException {
        String url = "https://diapuls.ru/product/test-poloski-ime-dc-50/";
        Document page = parse(new URL(url), 3000);
        Element price = page.select("div.price").first();
        return price.text();
    }

    public static String pageParseTestpoloska() throws IOException {
        String url = "https://www.test-poloska.ru/catalog/teststripes/imedc50.html";
        Document page = parse(new URL(url), 3000);
        Element price = page.select("tbody").get(10);
        Elements priceTS = price.getElementsByAttribute("nowrap");
        return Arrays.stream(priceTS.text()
                .split(" "))
                .skip(1)
                .reduce((x, y) -> x + " " + y)
                .stream()
                .findFirst()
                .get();
        }

    public static String pageParseMedMag() throws IOException {
        String url = "https://www.medmag.ru/index.php?productID=792&from=ya";
        Document page = parse(new URL(url), 3000);
        Element price = page.select("font.price").first();
        return price.text();
    }

    public static void main(String[] args) {
        Application.launch();
   }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setWidth(600);
        stage.setHeight(500);
        stage.setTitle("Поехали парсить сайты!!");
        Label diapuls = new Label("Цена полосок ime-dc на сайте diapuls.ru: "
                + pageParseDiapus()
                + System.lineSeparator()
                + "Цена полосок ime-dc на сайте medmag.ru:  "
                + pageParseMedMag()
                + System.lineSeparator()
                + "Цена полосок ime-dc на сайте test-poloska.ru: "
                + pageParseTestpoloska());

        diapuls.setAlignment(Pos.CENTER);

        Scene scene = new Scene(diapuls);
        stage.setScene(scene);
        stage.show();
    }
}



