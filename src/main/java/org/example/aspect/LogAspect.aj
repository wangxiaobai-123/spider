package org.example.aspect;

public aspect LogAspect {

    // 定义一个 PointCut，其名为 logPointcut
    // 该 PointCut 对应于指定 HelloWorld 对象的 sayHello 方法
    pointcut logPointCut():execution(void org.example.App.say());

    // 在 logPointcut 之后执行下面代码块
    after():logPointCut(){
        System.out.println("记录日志。。。。");
    }
}
