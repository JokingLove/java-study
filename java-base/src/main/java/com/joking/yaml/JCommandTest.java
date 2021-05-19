package com.joking.yaml;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * @author guoyongqiang
 */
public class JCommandTest {

    @Parameter(names = "--help", help = true)
    private boolean help;

    @Parameter(names = {"--hello", "-h"}, description = "这是一个hello参数")
    private String hello;

    public static void main(String[] args) {
        JCommandTest jCommandTest = new JCommandTest();
        jCommandTest.init(args);
    }

    private void init(String[] args) {
        JCommander jCommander = JCommander.newBuilder().addObject(this).build();
        jCommander.parse(args);
        System.out.println(hello);
        if(help) {
            jCommander.setProgramName("hehe");
            jCommander.usage();
            System.exit(0);
        }
    }
}
