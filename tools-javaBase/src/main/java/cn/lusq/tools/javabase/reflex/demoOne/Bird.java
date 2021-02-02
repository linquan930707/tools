package cn.lusq.tools.javabase.reflex.demoOne;

/**
 * lusq
 * 2021/2/2 14:47
 */
public class Bird  extends Animal implements Fly{
        public Bird() {
            System.out.println("bird 执行无参构造方法");
        }
        public Bird(String eat) {
            System.out.println(eat);
        }
        public Bird(String a,String b){
            System.out.println(String.format("bird 执行多个构造参数a=%s,b=%s",a,b));
        }
}
