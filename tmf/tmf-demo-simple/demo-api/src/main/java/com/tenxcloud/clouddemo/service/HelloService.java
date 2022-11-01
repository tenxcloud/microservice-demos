package com.tenxcloud.clouddemo.service;

public interface HelloService {
    String sayHello();

    String flowDemo();

    String degradeDemo();

    String paramFlowDemo(String p1, String p2, String p3);


}
