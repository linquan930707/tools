package cn.lusq.tools.designPatterns.singleton;

/**
 * lusq
 * 2020/12/29 17:07
 */
public class LazySingleton {

    private static  volatile LazySingleton instance = null;
    private LazySingleton(){}

    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }








}
