package org.example.neo4j;

import org.neo4j.driver.*;

import static org.neo4j.driver.Values.parameters;

// 用java和neo4j图形数据库建立连接
public class HelloWorldExample implements AutoCloseable {

    // 声明 neo4j的数据库驱动
    private final Driver driver;

    // 声明  当前类的构造方法，接收三个参数，数据库地址，用户名和密码
    public HelloWorldExample(String uri, String user, String password) {
        // 构造方法的作用就是给类变量：driver赋值，通过 类GraphDatabase的driver方法。接收数据库地址，用户名和 密码
        // 三个参数，得到neo数据库驱动Driver类的对象
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    // 关闭 这个类关闭这个类自己获取的neo4j数据库的方法
    public void close() throws Exception {
        // 调用Driver类的close方法，关闭数据库驱动，即关闭数据库链接
        driver.close();
    }

    // 声明一个方法 printGreeting，打印 打招呼的信息，接收一个String类型的参数  message
    public void printGreeting(final String message) {

        // 通过数据库驱动开启一个会话，通过会话对数据库进行具体的操作，插入，删除、查询数据
        try (Session session = driver.session()) {

            // writeTransaction启动了一个写事务。
            // writeTransaction()传入了一个lambda表达式
            // tx -> { ...... }
            // 来执行事务中的操作。
            String greeting = session.writeTransaction(tx -> {

                // 使用tx对象执行Cypher查询,run()会返回一个Result对象
                // run方法的第一个参数是 Cypher 语句 相当于 sql 语句
                // "CREATE (a:Greeting) " ：创建一个 Greeting 节点 , 这里的a 应该就是一个别名
                // "SET a.message = $message " ： 设置这个节点的message属性 以及给属性值赋值为$message
                // "RETURN a.message " : 返回消息内容
                // " from node " 相当于 sql 中查询时，从哪个表查询，这里就是从节点查询
                // 这个 from node 应该是和前面的 RETURN a.message 连起来的
                // " id(a) " 也是返回的值，返回节点 a 的 id
                // 第二个参数 "parameters("message", message)"
                // 是查询传入参数,message是外部定义的变量，
                // 会替换 Cypher 语句 "SET a.message = $message " 中的 $message
                Result result = tx.run(
                        "CREATE (a:Greeting) " + "SET a.message = $message "
                                + "RETURN a.message + ', from node ' + id(a)",
                        parameters("message", message));
                return result.single().get(0).asString();
            });
            System.out.println(greeting);
        }
    }

    public static void main(String... args) throws Exception {
        try (HelloWorldExample greeter = new HelloWorldExample("bolt://localhost:7687", "neo4j", "password")) {
            greeter.printGreeting("hello, world");
        }
    }
}