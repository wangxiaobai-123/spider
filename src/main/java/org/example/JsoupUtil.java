package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsoupUtil {

    public static void getAllText(Element element) {
        String text = element.ownText().trim();
        if (!text.isEmpty()) {
            System.out.println(text);
        }

        Elements children = element.children();
        for (Element child : children) {
            getAllText(child);
        }
    }

    public static List<String> getAllLink(String url) throws IOException {
        Document document = Jsoup.connect(url).get();

        Elements elements = document.select(".clearfix.jbk-nav-list.bor.f14");

        List<String> stringList = new ArrayList<>();
        for (Element element : elements) {
            Elements aList = element.select("a");
            for (Element a : aList) {
                String href = a.attr("href");

                int beginIndex = 0;
                int endIndex = url.indexOf(".com") + ".com".length();
                String webAddress = url.substring(beginIndex, endIndex);

                href = webAddress + href;
                stringList.add(href);
            }
        }
        return stringList;
    }

    // 都能用，因为都是.wrap.mt10.nav-bar
    public static String getPart(Document document) {
        Element first = document.select(".wrap.mt10.nav-bar").first();
        Elements spans = first.select("span");
        String partString = " ";
        for (Element span : spans) {
            String text = span.text();
            boolean chineseCharacter = RegexUtil.isChineseCharacter(text);
            if (chineseCharacter) {
                partString = text;
            }
        }

        return partString;
    }

    // 也能用，就是链接不一样
    public static List<String> getDiseaseList(Document document) {
        Elements elements = document.select(".ks-ill-list.clearfix.mt10");
        List<String> stringList = new ArrayList<>();
        // 遍历提取到的元素
        for (Element element : elements) {
            if (elements != null) {
                Elements aList = element.select("a");
                for (Element a : aList) {
                    String title = a.attr("title");
                    stringList.add(title);
                }
            }
        }
        return stringList;
    }

}
