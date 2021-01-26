package cn.lusq.tools.javabase.reflex.demoOne;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * lusq
 * 2021/1/7 14:58
 */
public class Alunbar {

    public static void main(String[] args) {
        Class<Alunbar> alunbarClass = Alunbar.class;
        //获取完整的包路径类名
        System.out.println(alunbarClass.getName());
        //获取简单的类名
        System.out.println(alunbarClass.getSimpleName());


        Class<Bird> birdClass = Bird.class;
        //获取类的修饰符
        System.out.println(birdClass.getModifiers());
        //Modifier 判断方法判断修饰符类型，Modifier可以用在方法和属性上
        System.out.println(Modifier.isPublic(birdClass.getModifiers()));
        System.out.println(String.format("------获取类的包名:%s",birdClass.getPackage()));
        System.out.println("==========================================================================");
        /*获取父类的class名*/
        Class<? super Bird> superclass = birdClass.getSuperclass();
        System.out.println(String.format("获取类的父类名称:%s",superclass.getSimpleName()));
        System.out.println("==========================================================================");
        /*获取实现的类 不会查找父类的实现的接口*/
        Class<?>[] interfaces = birdClass.getInterfaces();
        Arrays.asList(interfaces).forEach(face->{System.out.println(String.format("获取类的实现的接口:%s",face.getName())); });
        System.out.println("================================获取构造函数Constructor==========================================");
        //获取所有的声明的用public修改的构造函数
        Constructor<?>[] constructors = birdClass.getConstructors();
        //知道参数类型，获取对应的构造函数
        try {
            Constructor<Bird> constructor = birdClass.getConstructor(String.class);
            //获取构造函数参数的集合
//            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Bird bird =(Bird)constructor.newInstance("sdfsdfs");
            System.out.println(String.format("根据类获取已知构造函数，根据已知构造函数实例化类：%s",bird.toString()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    private class Bird extends Animal implements Fly{
        public Bird() {}
        public Bird(String eat) {
            System.out.println(eat);
        }
    }

    private class Animal implements Eat{}
    
    private interface Eat{}
    private interface Fly{}
    
    


}


