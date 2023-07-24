package org.example.repository;


import org.example.data.Disease;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DiseaseRepository {

    public static void insertAllDiseases(List<Disease> allDiseaseList) throws ClassNotFoundException, SQLException {
        // save allDiseaseList
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        // 连接数据库
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diseases", "root", "qinze@2018");

        // 插入数据
        for (Disease disease : allDiseaseList) {
            // 执行SQL查询
            String sqlInsert = "INSERT INTO xywy (id, parentId, name,type) VALUES (?,?,?,?)";
            // 创建 PreparedStatement 对象
            PreparedStatement stmt = conn.prepareStatement(sqlInsert);

            // 设置参数
            stmt.setString(1, String.valueOf(disease.getId()));
            stmt.setString(2, String.valueOf(disease.getParentId()));
            stmt.setString(3, disease.getName());
            stmt.setString(4, disease.getType());

            // 执行语句
            stmt.executeUpdate();

            stmt.close();
        }
        conn.close();
    }

}
