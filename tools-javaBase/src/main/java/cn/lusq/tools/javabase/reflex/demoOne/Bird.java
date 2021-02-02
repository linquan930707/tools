package cn.lusq.tools.javabase.reflex.demoOne;

/**
 * lusq
 * 2021/2/2 14:47
 */
@Test(name = "ddd",value = "ssss")
public class Bird  extends Animal implements Fly{

    private String age;

    private String name;

    public Bird() {
        System.out.println("bird 执行无参构造方法");
    }
    public Bird(String eat) {
        System.out.println(eat);
    }
    public Bird(String a,String b){
        System.out.println(String.format("bird 执行多个构造参数a=%s,b=%s",a,b));
    }


    @Override
    public void eat() {

    }

    @Override
    public void fly() {

    }


    public String eat(String shiwu){
        System.out.println("eat "+shiwu);
        return "调用Bird.eat";

    }


    public static String staticMethod(String s){
        System.out.println("staticMethod "+s);
        return "调用Bird.staticMethod";
    }




    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
