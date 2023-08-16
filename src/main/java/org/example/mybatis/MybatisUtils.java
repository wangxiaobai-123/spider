package org.example.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {

    static SqlSessionFactory sqlSessionFactory;

    static {
        InputStream inputStream;
        try{
            // 使用mybatis第一步：获取 sqlSessionFactory
            String resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch(IOException e){
            e.printStackTrace();
        } finally {

        }
    }


    // 既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
    // SqlSession 提供了在数据库执行SQL命令所需的所有方法。你可以通过SqlSession 实例来直接执行已映射的SQL语句
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
