package com.tenxcloud.http.demo.util;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TomcatUtils {
  @Value("${server.additionalPorts}")
  private String additionPorts;
  @Bean
  public TomcatServletWebServerFactory getApplicationContext(){
    TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
    Connector[] connector = this.additionalConnector();
    if (connector != null && connector.length > 0){
      webServerFactory.addAdditionalTomcatConnectors(connector);
    }
    return webServerFactory;
  }
  private Connector[] additionalConnector(){
    if (StringUtils.isEmpty(additionPorts)){
      return null;
    }
    String[] ports = this.additionPorts.split(",");
    List<Connector> list = new ArrayList<>();
    for (String port : ports) {
      Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
      connector.setScheme("http");
      connector.setPort(Integer.parseInt(port));
      list.add(connector);
    }
    return list.toArray(new Connector[]{});
  }
}
