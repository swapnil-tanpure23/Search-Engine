package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Crawler {
    HashSet<String> urlSet;
    int maxDepth=2;
    Crawler(){
        urlSet = new HashSet<>();
    }
    public void getPagesandlinks(String urls, int depth){
        if(urlSet.contains(urls)) return;
        if(depth>maxDepth) return;
        if(urlSet.add(urls)) System.out.println(urls);
        depth++;
        try {
            Document document = Jsoup.connect(urls).timeout(5000).get();

            Indexer indexer = new Indexer(document, urls);

            System.out.println(document.title());

            Elements availblelink = document.select("a[href]");
            for (Element currentLink : availblelink) {
                getPagesandlinks(currentLink.attr("abs:href"), depth);
            }
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.getPagesandlinks("https://www.javatpoint.com/",0);
    }
}