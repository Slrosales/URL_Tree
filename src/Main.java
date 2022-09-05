import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException{
        Scanner read = new Scanner(System.in);
        String url = read.nextLine();

        if (statusCode(url) == 200){
            Document doc = getHTML(url);
            Document docs = Jsoup.parse(doc.toString());

            File file = new File("DOM.txt");
            PrintStream stdout = System.out;
            PrintStream stream;

            {
                try {
                    stream = new PrintStream(file);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            System.setOut(stream);

            docs.traverse(new NodeVisitor(){
                int num = 0;
                @Override
                public void head(Node node, int depth){
                    if(!node.nodeName().contains("#")){
                        System.out.println(num + node.nodeName());
                        num++;
                    }
                }

                @Override
                public void tail(Node node, int depth){
                    if(!node.nodeName().contains("#")){
                        System.out.println(num+"/" + node.nodeName());
                        num++;
                    }
                }


            });

            System.setOut(stdout);

            try {
                File parsedFile = new File(file.getAbsolutePath());
                Scanner reader = new Scanner(parsedFile);
                while (reader.hasNextLine()){
                    String data = reader.nextLine();
                }
                reader.close();
            } catch (FileNotFoundException e){
                System.out.println("error");
            }

        }

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

        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(1000000).get();
        } catch (IOException ex){
            System.out.println("Error");
        }
        return doc;
    }
}