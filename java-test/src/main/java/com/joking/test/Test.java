package com.joking.test;

import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.StandardEnvironment;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class Test {


    @PostConstruct
    public void init() {
        System.out.println("init 方法调用！");
    }

    static final  Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");

    private static final String  DIR = "META-INF/test/";

    public static void mai2n(String[] args) throws IOException {
        String name = "hah,,aha";
        String[] split = NAME_SEPARATOR.split(name);
        Arrays.asList(split).forEach(System.out::println);
        ClassLoader classLoader = Test.class.getClassLoader();
        if(classLoader != null) {
            Enumeration<URL> urls = classLoader.getResources(DIR + Animal.class.getName());
            while(urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if(url != null) {
                    InputStream inputStream = url.openStream();

                }
            }
        }

    }

    public static void main1(String[] args) {
        String workNo = "20209100293";
        Integer reportType = 1;
        StringJoiner nameJoiner = new StringJoiner("_", "", ".pdf");
        // workNo_millis_reportType.pdf
        String fileName = nameJoiner.add(workNo).add(String.valueOf(System.currentTimeMillis()))
                .add(reportType.toString()).toString();
        System.out.println(fileName);
    }

    public static void main2(String[] args) {
        SpringBootBanner springBootBanner = new SpringBootBanner();
        StandardEnvironment environment = new StandardEnvironment();
        springBootBanner.printBanner(environment, springBootBanner.getClass(), System.out);

        PrintStream printStream = System.out;
        printStream.println(AnsiOutput.toString(AnsiColor.BLUE, "SPRING_BOOT", AnsiColor.RED,
                AnsiStyle.BOLD, "version"));
    }

    public static void main(String[] args) {
        System.out.println("Hello,Akina!");
        System.out.println("\033[30;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[31;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[32;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[33;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[34;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[35;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[36;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[37;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[40;31;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[41;32;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[42;33;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[43;34;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[44;35;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[45;36;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[46;37;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[47;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[90;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[91;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[92;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[93;4m" + "Hello,Akina!" + "\033[0m");
        System.out.println("\033[94;4m" + "Hello,Akina!" + "\033[0m");

    }
}
