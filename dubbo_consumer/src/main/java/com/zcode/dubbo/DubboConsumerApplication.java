package com.zcode.dubbo;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.zcode.dubbo.util.MyUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:dubbo.xml"})
@DubboComponentScan(basePackages = "com.zcode.dubbo")
public class DubboConsumerApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DubboConsumerApplication.class, args);
		MyUtil.setContext(context);
	}
}
