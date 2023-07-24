package org.example;

import org.example.aspect.TimeAspect;
import org.example.data.Disease;
import org.example.repository.DiseaseRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.security.provider.ConfigFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Hello world!
 *
 */
public class App 
{


    public static void say() {
        System.out.println("hello");
    }

    public static void main( String[] args ) throws IOException, SQLException, ClassNotFoundException {

        App.say();

//        AnnotationConfig config = new AnnotationConfiguration();
//        config.aspectOf(TimeAspect.class);

        long startTime = System.currentTimeMillis();

        // 获取疾病
        Map<String, List<Disease>> diseaseMap = SpiderService.getDiseaseMap();

        // 获取症状
        Map<String, List<Disease>> symptomMap = SpiderService.getSymptomMap();

        Map<String, List<String>> partMap = new HashMap<>();
        String[] partList = new String[]{"鼻", "耳", "眼", "口"};
        List<String> headPartList = Arrays.asList(partList);
        partMap.put("头部", headPartList);
        String[] partList2 = new String[]{"上肢","下肢"};
        List<String> fourPartList = Arrays.asList(partList2);
        partMap.put("四肢", fourPartList);

        List<Disease> diseaseList = new ArrayList<>();


        // 存疾病
        for (Map.Entry<String, List<Disease>> entry : diseaseMap.entrySet()) {
            List<Disease> list = entry.getValue();
            diseaseList.addAll(list);
        }

        // 存症状
        for (Map.Entry<String, List<Disease>> entry : symptomMap.entrySet()) {
            List<Disease> list = entry.getValue();
            diseaseList.addAll(list);
        }

        // 存部位
        for (Map.Entry<String, List<Disease>> entry : diseaseMap.entrySet()) {
            String key = entry.getKey();
            diseaseList.add(new Disease(0, key, Constants.BODY_PART));
        }
        diseaseList.add(new Disease(0, "头部", Constants.BODY_PART));
        diseaseList.add(new Disease(0, "四肢", Constants.BODY_PART));

        // id赋值
        for (int i = 0; i < diseaseList.size(); i++) {
            Disease disease = diseaseList.get(i);
            disease.setId(i+1);
        }

        // 部位的父id赋值好了
        for (Map.Entry<String, List<String>> entry : partMap.entrySet()) {
            String key = entry.getKey();
            int keyId = diseaseList.stream()
                    .filter(disease -> disease.getName().equals(key))
                    .collect(Collectors.toList()).get(0).getId();

            List<String> keyList = entry.getValue();
            for (String s : keyList) {
                diseaseList.stream()
                        .filter(disease -> disease.getName().equals(s))
                        .collect(Collectors.toList()).get(0).setParentId(keyId);
            }
        }

        // 疾病和症状的父id
        for (Map.Entry<String, List<Disease>> diseaseListEntry : diseaseMap.entrySet()) {
            String key = diseaseListEntry.getKey();

            int parentId = diseaseList.stream()
                    .filter(disease -> disease.getName().equals(key))
                    .collect(Collectors.toList()).get(0).getId();

            for (Disease disease : diseaseListEntry.getValue()) {
                disease.setParentId(parentId);
            }
        }

        for (Map.Entry<String, List<Disease>> diseaseListEntry : symptomMap.entrySet()) {
            String key = diseaseListEntry.getKey();
            int parentId = diseaseList.stream()
                    .filter(disease -> disease.getName().equals(key))
                    .collect(Collectors.toList()).get(0).getId();

            for (Disease disease : diseaseListEntry.getValue()) {
                disease.setParentId(parentId);
            }
        }


        long endTime = System.currentTimeMillis();

        int dataSize = diseaseList.size(); // 计算数据量
        long usedTime = endTime - startTime; // 计算用时



        DiseaseRepository.insertAllDiseases(diseaseList);
        // 记录日志信息
//        System.out.println("插入 "+dataSize+" 条数据，用时 "+usedTime+" 毫秒。" );

    }



}
