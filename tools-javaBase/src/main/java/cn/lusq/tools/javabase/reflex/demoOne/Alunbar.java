package cn.lusq.tools.javabase.reflex.demoOne;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

/**
 * lusq
 * 2021/1/7 14:58
 */
public class Alunbar {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
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
            Bird bird =(Bird)constructor.newInstance("bird 执行有参构造方法");
            System.out.println(String.format("根据类获取已知构造函数，根据已知构造函数实例化类：%s",bird.toString()));

            Constructor<Bird> constructorTwo = birdClass.getConstructor(String.class, String.class);
            Bird birdTwo = constructorTwo.newInstance("ddd", "fff");

            Constructor<Bird> constructorZero = birdClass.getConstructor();
            Bird birdZero = constructorZero.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        System.out.println("================================获取到该类的声明的成员方法信息==========================================");
        /*无参的getMethods()和getDeclaredMethods()都只能获取到类声明的成员方法，不能获取到继承父类的方法。*/
        /*  无参的getMethods()获取到所有public修饰的方法，返回的是Method[]数组  */
        Method[] methods = birdClass.getMethods();
        /*  无参的getDeclaredMethods()方法到的是所有的成员方法，和修饰符无关  */
        Method[] declaredMethods = birdClass.getDeclaredMethods();
        /*  对于有参的getMethods()方法，必须提供要获取的方法名以及方法名的参数。如果要获取的方法没有参数，则用null替代   */
        Method eatMethod = birdClass.getMethod("eat", String.class);
        /*获取参数类型*/
        Class<?>[] parameterTypes = eatMethod.getParameterTypes();
        System.out.println(parameterTypes[0].getName());
        /*获取方法的返回值*/
        Class<?> returnType = eatMethod.getReturnType();
        System.out.println(returnType.getName());
        /*
         * invoke方法有两个参数，第一个参数是要调用方法的对象，上面的代码中就是Bird的对象，
         * 第二个参数是调用方法要传入的参数。如果有多个参数，则用数组。
         * 如果调用的是static方法，invoke()方法第一个参数就用null代替：
         */
        Constructor<Bird> constructor = birdClass.getConstructor();
        System.out.println(eatMethod.invoke(constructor.newInstance(), "ssss"));

        Method staticMethod = birdClass.getMethod("staticMethod", String.class);
        System.out.println(staticMethod.invoke(null,"静态方法"));


        /**
         * 获取成员变量
         * 通过反射可以在运行时获取到类的所有成员变量，还可以给成员变量赋值和获取成员变量的值
         * getFields()方法获取所有public修饰的成员变量，getField()方法需要传入变量名，并且变量必须是public修饰符修饰。
         * getDeclaredFields方法获取所有生命的成员变量，不管是public还是private。
         */
        Class<Bird> birdClass1 = Bird.class;
        Field[] fields = birdClass1.getFields();
        Field[] declaredFields = birdClass1.getDeclaredFields();
//        Field ageField = birdClass1.getField("age");
        Field aageFieldge1 = birdClass1.getDeclaredField("age");
       /*允许修改和写入 setAccessible(true)方法，这是针对私有变量而言，public和protected等都不需要。这个方法是允许通过反射访问类的私有变量*/
        aageFieldge1.setAccessible(true);
        /*获取成员变量类型*/
        Class<?> type = aageFieldge1.getType();
        /*成员变量的赋值和取值*/
        Bird bird3 = new Bird();
        bird3.setAge("12312");
        System.out.println("根据Field 获取值："+(String)aageFieldge1.get(bird3));
        aageFieldge1.set(bird3,"43567");
        System.out.println("根据Field 赋值:"+bird3.getAge());


        System.out.println("================================访问类注解信息==========================================");
        Class<Bird> birdClass4 = Bird.class;
        Annotation[] annotations = birdClass4.getAnnotations();
        Test annotation = birdClass4.getAnnotation(Test.class);
        System.out.println("annotation.name:"+annotation.name());
        System.out.println("annotation.value:"+annotation.value());
        Method eatMethod1 = birdClass4.getMethod("eat", String.class);
        Annotation[] annotations1 = eatMethod1.getAnnotations();
        Test annotation1 = eatMethod1.getAnnotation(Test.class);
        //获取方法参数的注解
        Annotation[][] parameterAnnotations = eatMethod1.getParameterAnnotations();






    }



    public void invokeMethod(Class<?>cls){
        Method[] methods = cls.getMethods();
        for(Method method:methods){
            System.out.println("method name:"+method.getName());
            System.out.println("method paramerTypes:"+method.getParameterTypes());
            System.out.println("method return:"+method.getReturnType());
        }



    }



    
    


}


