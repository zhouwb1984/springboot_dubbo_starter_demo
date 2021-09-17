package com.zcode.dubbo.generic;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.zcode.dubbo.controller.MyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 测试泛化调用远程服务
 *
 * 本例调用远程com.zcode.dubbo.generic.GenericPayService接口服务
 * 在本程序中并看不到该接口。
 *
 * dubbo提供泛化服务接口GenericService
 *
 * Created by zhouwb on 2018/10/15.
 */
@RestController
public class GenericCallDemo {

    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    // 这个样子获取不到GenericService，不知道为什么
//    @Reference(
//            interfaceName = "com.zcode.dubbo.generic.GenericPayService",
//            generic = true
//    )
//    private GenericService payService;

    /**
     * 测试泛化调用
     * @return
     */
    @RequestMapping("/pay")
    public String pay(){

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("com.zcode.dubbo.generic.GenericPayService");
        reference.setGeneric(true); //声明为泛化接口
        //获取泛化接口
        GenericService payService = reference.get();
        //调用远程方法
        String msg = (String)payService.$invoke("pay", null, null);
        logger.info("dubbo泛化调用：");
        logger.info(msg);
        return  msg;
    }

}
