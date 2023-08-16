package org.example.repository;


import org.example.dao.entity.Disease;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiseaseRepository {

//    public static Connection conn;
//
//    static {
//        // save allDiseaseList
//        try {
//            Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
//            // 连接数据库
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diseases", "root", "qinze@2018");
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }


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


    public static List<String> getAllDiseasesFromDB() throws ClassNotFoundException, SQLException {

        // save allDiseaseList
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        // 连接数据库
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diseases", "root", "qinze@2018");

        // 插入数据

        // 执行SQL查询
        String sqlInsert = "SELECT * FROM xywy ";
        // 创建 PreparedStatement 对象
        PreparedStatement stmt = conn.prepareStatement(sqlInsert);
        // 执行语句
        ResultSet rs = stmt.executeQuery();

        List<String> nameList = new ArrayList<>();
        while (rs.next()) {
            // 获取数据
            String name = rs.getString("name");

            nameList.add(name);
        }

        stmt.close();

        conn.close();

        return nameList;

    }


    public static void updateManDiseases(List<String> diseaseNameList) throws ClassNotFoundException, SQLException {

        // save allDiseaseList
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        // 连接数据库
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diseases", "root", "qinze@2018");

        String sql = "UPDATE xywy SET sex = ? WHERE name = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "male"); // 设置第一个参数

        int count = 0;
        for (String name : diseaseNameList) {
            pstmt.setString(2, name); // 设置第二个参数
            int rowsUpdated = pstmt.executeUpdate(); // 执行更新
            count = count + rowsUpdated;
        }
        System.out.println(count);

        pstmt.close();

        conn.close();

    }
    public static void updateWomanDiseases(List<String> diseaseNameList) throws ClassNotFoundException, SQLException {

        // save allDiseaseList
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        // 连接数据库
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diseases", "root", "qinze@2018");

        String sql = "UPDATE xywy SET sex = ? WHERE name = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "female"); // 设置第一个参数

        int count = 0;
        for (String name : diseaseNameList) {
            pstmt.setString(2, name); // 设置第二个参数
            int rowsUpdated = pstmt.executeUpdate(); // 执行更新
            count = count + rowsUpdated;
        }
        System.out.println(count);

        pstmt.close();
        conn.close();

    }


    public static void updateChildrenDiseases(List<String> diseaseNameList) throws ClassNotFoundException, SQLException {

        // save allDiseaseList
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        // 连接数据库
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diseases", "root", "qinze@2018");

        String sql = "UPDATE xywy SET age_group = ? WHERE name = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "children"); // 设置第一个参数

        int count = 0;
        for (String name : diseaseNameList) {
            pstmt.setString(2, name); // 设置第二个参数
            int rowsUpdated = pstmt.executeUpdate(); // 执行更新
            count = count + rowsUpdated;
        }
        System.out.println(count);

        pstmt.close();

        conn.close();

    }
    public static void updateOldDiseases(List<String> diseaseNameList) throws ClassNotFoundException, SQLException {

        // save allDiseaseList
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        // 连接数据库
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/diseases", "root", "qinze@2018");

        String sql = "UPDATE xywy SET age_group = ? WHERE name = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "old"); // 设置第一个参数

        int count = 0;
        for (String name : diseaseNameList) {
            pstmt.setString(2, name); // 设置第二个参数
            int rowsUpdated = pstmt.executeUpdate(); // 执行更新
            count = count + rowsUpdated;
        }
        System.out.println(count);

        pstmt.close();

        conn.close();

    }


}
