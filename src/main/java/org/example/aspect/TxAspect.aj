package org.example.aspect;

public aspect TxAspect {
    void around():call(void org.example.App.say()){
        System.out.println("开始事务。。。");
        proceed();
        System.out.println("结束事务。。。");
    }
}
