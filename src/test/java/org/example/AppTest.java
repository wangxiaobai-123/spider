package org.example;

import static org.junit.Assert.assertTrue;

import org.apache.ibatis.session.SqlSession;
import org.example.dao.DiseaseDao;
import org.example.dao.DiseaseMapper;
import org.example.dao.entity.Disease;
import org.example.mybatis.MybatisUtils;
import org.example.repository.DiseaseRepository;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    public static final String P_HTML = "/p/(.*?).html";
    @Resource
    private DiseaseDao diseaseDao;

    @Test
    public void updateMan() throws IOException, SQLException, ClassNotFoundException {
        SpiderService spiderService = new SpiderService();
        String nankeLink = spiderService.getLinkOfDepartment("nanke");
        System.out.println(nankeLink);

        List<String> diseaseNameListOf = spiderService.getDiseaseNameListOf("nanke");

        List<String> allDiseasesFromDB = DiseaseRepository.getAllDiseasesFromDB();

        boolean b = allDiseasesFromDB.retainAll(diseaseNameListOf);

        for (String s : allDiseasesFromDB) {
            System.out.println(s);
        }

        DiseaseRepository.updateManDiseases(allDiseasesFromDB);


//        System.out.println(set.toString());


//        System.out.println(elementsByClass.toString());


//        List<String> diseaseListOf = JsoupUtil.getDiseaseNameListOf(nankeLink);
    }

    @Test
    public void updateWoMan() throws IOException, SQLException, ClassNotFoundException {
        SpiderService spiderService = new SpiderService();
        String link = spiderService.getLinkOfDepartment("fuchanke");
        System.out.println(link);

        Document documentOfFuchanke = Jsoup.connect(link).get();

        Elements fb_f14 = documentOfFuchanke.getElementsByClass("fb f14");
        List<String> urlList = new ArrayList<>();
        for (Element element : fb_f14) {
            Elements a = element.getElementsByTag("a");
            for (Element element1 : a) {
                String title = element1.attr("href");
                urlList.add(title);
            }
        }
        System.out.println(urlList.toString());
        String departmentName = null;
        List<String> allDiseaseNameListOfFuchanke = new ArrayList<>();
        for (String s : urlList) {
            String s1 = RegexUtil.extractSubstring(s, "\\/p\\/(.*?)\\.html");
            System.out.println(s1);
            departmentName = s1;
            List<String> diseaseNameListOf = spiderService.getDiseaseNameListOf(departmentName);
            allDiseaseNameListOfFuchanke.addAll(diseaseNameListOf);
        }
        List<String> allDiseasesFromDB = DiseaseRepository.getAllDiseasesFromDB();
        boolean b = allDiseasesFromDB.retainAll(allDiseaseNameListOfFuchanke);
        for (String s : allDiseasesFromDB) {
            System.out.println(s);
        }

        DiseaseRepository.updateWomanDiseases(allDiseasesFromDB);

    }

    @Test
    public void updateChildren() throws IOException, SQLException, ClassNotFoundException {
        SpiderService spiderService = new SpiderService();
        String link = spiderService.getLinkOfDepartment("erke");
        System.out.println(link);

//        Document document = Jsoup.connect(link).get();
//        System.out.println(document.toString());


        List<String> diseaseNameList = new ArrayList<>();

        Document document = Jsoup.connect(link).get();
//        Elements elementsByClass = document.getElementsByClass("");
//
//        for (Element element : elementsByClass) {
//            Elements a = element.getElementsByTag("a");
//            for (Element element1 : a) {
//                String title = element1.attr("title");
//                diseaseNameList.add(title);
//            }
//        }
//
////        "jbk-sed-menu bor pa none f12"
//        Elements elementsByClass1 = document.getElementsByClass("gre");
//        for (Element element : elementsByClass1) {
//            String href = element.attr("href");
//            String linkOfElement = "http://zzk.xywy.com" + href;
//
//            Document documentOfErke = Jsoup.connect(linkOfElement).get();
//            Elements erkeDiseaseElement = documentOfErke.getElementsByClass("ks-ill-list clearfix mt10");
//            for (Element element1 : erkeDiseaseElement) {
//                String title = element1.getElementsByTag("a").attr("title");
//                diseaseNameList.add(title);
//            }
//        }
//
//        Set<String> set = diseaseNameList.stream().collect(Collectors.toSet());
//        diseaseNameList = new ArrayList<>(set);
//        for (String s : diseaseNameList) {
//            System.out.println(s);
//        }


//
//        List<String> allDiseasesFromDB = DiseaseRepository.getAllDiseasesFromDB();
//
//        boolean b = allDiseasesFromDB.retainAll(diseaseNameListOf);
//
//        for (String s : allDiseasesFromDB) {
//            System.out.println(s);
//        }

//        DiseaseRepository.updateDiseases(allDiseasesFromDB);


//        System.out.println(set.toString());


//        System.out.println(elementsByClass.toString());


//        List<String> diseaseListOf = JsoupUtil.getDiseaseNameListOf(nankeLink);
        Elements fb_f14 = document.getElementsByClass("fb f14");
        List<String> urlList = new ArrayList<>();
        for (Element element : fb_f14) {
            Elements a = element.getElementsByTag("a");
            for (Element element1 : a) {
                String title = element1.attr("href");
                urlList.add(title);
            }
        }
        System.out.println(urlList.toString());
        String departmentName = null;
        List<String> allDiseaseNameListOfFuchanke = new ArrayList<>();
        for (String s : urlList) {
            String s1 = RegexUtil.extractSubstring(s,
                    P_HTML);
            System.out.println(s1);
            departmentName = s1;
            List<String> diseaseNameListOf = spiderService.getDiseaseNameListOf(departmentName);
            allDiseaseNameListOfFuchanke.addAll(diseaseNameListOf);
        }
//        List<String> allDiseasesFromDB = DiseaseRepository.getAllDiseasesFromDB();
//        boolean b = allDiseasesFromDB.retainA
//        ll(allDiseaseNameListOfFuchanke);
//        for (String s : allDiseasesFromDB) {
//            System.out.println(s);
//        }
//
//        DiseaseRepository.updateChildrenDiseases(allDiseasesFromDB);

    }

    @Test
    public void testRegex() {
        String s1 = RegexUtil.extractSubstring("/p/erkezonghe.html", "/p/(.*).html");
        System.out.println(s1);
    }

    @Test
    public void updateOld() throws IOException, SQLException, ClassNotFoundException {
        SpiderService spiderService = new SpiderService();
        String nankeLink = spiderService.getLinkOfDepartment("nanke");
        System.out.println(nankeLink);

        List<String> diseaseNameListOf = spiderService.getDiseaseNameListOf("nanke");

        List<String> allDiseasesFromDB = DiseaseRepository.getAllDiseasesFromDB();

        boolean b = allDiseasesFromDB.retainAll(diseaseNameListOf);

        for (String s : allDiseasesFromDB) {
            System.out.println(s);
        }
        DiseaseRepository.updateOldDiseases(allDiseasesFromDB);

//        DiseaseRepository.updateDiseases(allDiseasesFromDB);


//        System.out.println(set.toString());


//        System.out.println(elementsByClass.toString());


//        List<String> diseaseListOf = JsoupUtil.getDiseaseNameListOf(nankeLink);
    }

    @Test
    public  void test(){

        // 第一步：获得sqlsession 对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //方式一： getMapper
        DiseaseMapper userMapper = sqlSession.getMapper(DiseaseMapper.class);
        List<Disease> diseaseList = diseaseDao.getDiseaseList();

        //方式二： getMapper 老版本不推荐
        //List<User> userList = sqlSession.selectList("com.ctra.dao.UserMapper.getUserList");

        for (Disease disease : diseaseList){
            System.out.println(disease);
        }

        //  关闭 sqlsession
        sqlSession.close();
    }


    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        List<Disease> diseaseList = new ArrayList<>();
        Disease disease = new Disease( "脑癌", 8119,Constants.BODY_DISEASE);

        diseaseList.add(disease);
        diseaseList.add(new Disease( "神经性头痛", 8119,Constants.BODY_DISEASE));
        diseaseList.add(new Disease( "脑炎", 8119,Constants.BODY_DISEASE));
        diseaseList.add(new Disease( "闭锁综合征", 8119,Constants.BODY_DISEASE));
        DiseaseRepository.insertAllDiseases(diseaseList);
    }

    @Test
    public void say() {
        App.say();
    }

    @Test
    public void cutString() {
        String url = "http://jib.xywy.com/html/jingbu.html";
        int beginIndex = 0;
        int endIndex = url.indexOf(".com") + ".com".length();
        String webAddress = url.substring(beginIndex, endIndex);
        System.out.println(webAddress);
    }

    @Test
    public void selectURL() throws IOException {
//        "view-source:http://zzk.xywy.com/p/bi.html"
        List<String> allLink = JsoupUtil.getAllLink("http://zzk.xywy.com/p/bi.html");
        System.out.println(allLink);
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
