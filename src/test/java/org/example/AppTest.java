package org.example;

import static org.junit.Assert.assertTrue;

import org.example.data.Disease;
import org.example.repository.DiseaseRepository;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

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
