import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Main{
    public static Tree tree = new Tree();
    public static void main(String[] args) throws IOException{
        Scanner read = new Scanner(System.in);

        String url = read.nextLine();
        while (urlValidator(url) != true){
            url = read.nextLine();
        }

        if (statusCode(url) == 200){

            Document doc = getHTML(url);
            Document docs = Jsoup.parse(doc.toString());
            //System.out.println(docs);

            webCrawler(docs.getElementsByTag("html").get(0), null);

            //System.out.println(tree.Height(tree.getRoot()));
            //System.out.println((tree.getRoot().child.size()));

        }
    }

    public static boolean urlValidator(String url)
    {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (URISyntaxException exception) {
            return false;
        }
        catch (MalformedURLException exception) {
            return false;
        }
    }

    public static void webCrawler (Element element, String father){
        if (element != null) {
            tree.insert(element.nodeName(), father);
        } else {
            return;
        }

        if (element.childrenSize() > 0){
            if (element.firstElementChild() != null && element.nodeName() != null)
                System.out.println(element.firstElementChild().nodeName()+ " " +element.nodeName());
            webCrawler(element.firstElementChild(), element.nodeName());
        }
        if (element.nextElementSibling() != null && element.nodeName() != null)
            System.out.println("xd    "+element.nextElementSibling().nodeName()+ " " +element.nodeName());
        webCrawler(element.nextElementSibling(), father);
    }

    public static int statusCode(String url){
        Connection.Response response = null;

        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(1000000).ignoreHttpErrors(true).execute();
        } catch (IOException ex){
            System.out.println("Error");
        }

        return response.statusCode();
    }

    public static Document getHTML(String url){
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(1000000).get();
            return doc;
        } catch (IOException ex){
            return null;
        }
    }
}