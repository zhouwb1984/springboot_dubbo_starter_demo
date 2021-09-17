package com.zcode.dubbo.callevent;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.zcode.dubbo.controller.MyController;
import com.zcode.dubbo.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试客户端事件回调，<dubbo:method name="count" onreturn="对象.方法" />
 *
 * onreturn参数
 *
 * Created by zhouwb on 2018/10/15.
 */
@RestController
public class CEController {

    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    /*
        指示count()方法开启事件回调
        想尝试通过注解的形式实现这个功能，测试发现是徒劳无功的，Dubbo目前的版本不支持，对MethodConfig的注解实现支持不到位
        看一下这篇文章：https://blog.csdn.net/xiao_jun_0820/article/details/81452722
        还是要通过传统的XML进行配置或者API配置
    @Reference(
            cache = "lru",  //启用缓存
            parameters = {
                    "count.async", "true",
                    "count.onreturn", "callEventHandler.countReturn"
            }
    )
    private BillService billService;
    */

    /*
        在XML中对billService_xml进行配置，指定某方法开启事件回调
     */
    @Resource
    private BillService billService_xml;

    /*
        通过API的形式进行配置，指定某方法开启事件回调
        billServiceReferenceConfig在DubboConfig文件中配置
     */
    @Resource
    private ReferenceConfig<BillService> billServiceReferenceConfig;

    /**
     * 测试methodConfig 的 onreturn参数
     *
     * onreturn参数指定服务器执行完毕后的回调方法
     *
     * @return
     */
    @RequestMapping("/onreturn")
    public String onreturn(){

        //count被指定为异步回调，见@Referenc的配置
        billService_xml.count("【XML】");
        billServiceReferenceConfig.get().count("【API】");

        billService_xml.findOne("【XML】");
        billServiceReferenceConfig.get().findOne("【API】");

        //看他们俩是不是一个对象
        logger.info(billService_xml.hashCode() + "");
        logger.info(billServiceReferenceConfig.get().hashCode() + "");

        return "onreturn successfully!";
    }

}
