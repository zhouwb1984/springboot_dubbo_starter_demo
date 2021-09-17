package com.zcode.dubbo.config;

import com.alibaba.dubbo.config.MethodConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.zcode.dubbo.callevent.CallEventHandler;
import com.zcode.dubbo.service.BillService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by zhouwb on 2018/10/15.
 */
@Configuration
public class DubboConfig {

    /**
     * 通过API的形式配置ReferenceConfig，指定某一方法启用事件回调
     *
     * @return
     */
    @Bean
    public ReferenceConfig<BillService> billServiceReferenceConfig(
            /*@RequestParam("callEventHandler")*/  CallEventHandler callEventHandler){

        ReferenceConfig<BillService> reference = new ReferenceConfig<>();
        reference.setId("billService_api"); //这是BIllService的Bean ID，程序中通过这个ID注入
        reference.setInterface(BillService.class);

        List<MethodConfig> methodList = new ArrayList<>();
        reference.setMethods(methodList);

        //方法配置
        MethodConfig findOne = new MethodConfig();
        findOne.setName("findOne");
        findOne.setAsync(true); //异步调用
        //事件回调-callEventHandler.findOneReturn()
        findOne.setOnreturn(callEventHandler);
        findOne.setOnreturnMethod("findOneReturn");
        methodList.add(findOne);

        return reference;
    }

}
