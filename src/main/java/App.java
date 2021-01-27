import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld beanFirst =
                (HelloWorld) applicationContext.getBean("helloworld");
        HelloWorld beanSecond =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(beanFirst.equals(beanSecond));
        Cat catFirst =
                (Cat) applicationContext.getBean("cat");
        Cat catSecond =
                (Cat) applicationContext.getBean("cat");
        System.out.println(catFirst.equals(catSecond));

    }
}