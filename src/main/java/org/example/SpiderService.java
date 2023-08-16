package org.example;

import org.example.dao.entity.Disease;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SpiderService {
    public static final String DISEASE_URL = "http://jib.xywy.com/html/jingbu.html";
    public static final String SYMPTOM_URL = "http://zzk.xywy.com/p/bi.html";



    public static Map<String, List<Disease>> getDiseaseMap() throws IOException {
        List<String> allLink = JsoupUtil.getAllLink(DISEASE_URL);
        Map<String, List<Disease>> diseaseListMap = new HashMap<>();
        for (String link : allLink) {

            // 一个方法，返回一个map，key是部位名称，value是对应的疾病集合
            Document document = Jsoup.connect(link).get();

            // 先找部位
            String part = JsoupUtil.getPart(document);

            // 再找部位对应的症状集合






























            List<String> diseaseStringList = JsoupUtil.getDiseaseNameList(document);
            Set<String> set = new HashSet<>(diseaseStringList);
            List<String> distinctList = new ArrayList<>(set);

            // 生成List<Disease>
            List<Disease> diseaseList = new ArrayList<>();
            for (String diseaseName : distinctList) {
                diseaseList.add(new Disease(0, diseaseName, Constants.BODY_DISEASE));
            }



            if (distinctList.isEmpty() == false) {
                diseaseListMap.put(part, diseaseList);
            }
        }

        return diseaseListMap;
    }



    public static Map<String, List<Disease>> getSymptomMap() throws IOException {
        List<String> allLink = JsoupUtil.getAllLink(SYMPTOM_URL);
        Map<String, List<Disease>> diseaseListMap = new HashMap<>();
        for (String link : allLink) {

            // 一个方法，返回一个map，key是部位名称，value是对应的疾病集合
            Document document = Jsoup.connect(link).get();

            // 先找部位
            String part = JsoupUtil.getPart(document);

            // 再找部位对应的症状集合
            List<String> diseaseStringList = JsoupUtil.getDiseaseNameList(document);
            Set<String> set = new HashSet<>(diseaseStringList);
            List<String> distinctList = new ArrayList<>(set);

            List<Disease> diseaseList = new ArrayList<>();
            for (String symptomName : distinctList) {
                diseaseList.add(new Disease(0, symptomName, Constants.BODY_SYMPTOM));
            }


            if (diseaseStringList.isEmpty() == false) {
                diseaseListMap.put(part, diseaseList);
            }

        }

        return diseaseListMap;
    }

    List<String> getDiseaseNameListOf(String department) throws IOException {

        List<Disease> diseaseList = new ArrayList<>();

        String linkOfDepartment = getLinkOfDepartment(department);


        List<String> diseaseNameList;

        Document document = Jsoup.connect(linkOfDepartment).get();
        Elements elementsByClass = document.getElementsByClass("ks-ill-list clearfix mt10");
        List<String> stringList = new ArrayList<>();
        for (Element element : elementsByClass) {
            Elements a = element.getElementsByTag("a");
            for (Element element1 : a) {
                String title = element1.attr("title");
                stringList.add(title);
            }
        }
        Set<String> set = stringList.stream().collect(Collectors.toSet());
        diseaseNameList = new ArrayList<>(set);

        return diseaseNameList;
    }

    public String getLinkOfDepartment(String department) {
        String linkOfDepartment = null;

        linkOfDepartment = "http://zzk.xywy.com/p/" + department + ".html";

        return linkOfDepartment;
    }

}
