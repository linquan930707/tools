package cn.lusq.tools.designPatterns.singleton;

/**
 * lusq
 * 2020/12/29 17:11
 */
public class HungrySingleton {

    private static volatile HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance(){
        return instance;
    }




}
