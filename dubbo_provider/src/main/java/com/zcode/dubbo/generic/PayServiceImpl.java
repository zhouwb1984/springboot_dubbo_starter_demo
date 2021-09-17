package com.zcode.dubbo.generic;

import com.alibaba.dubbo.config.annotation.Service;

/**
 *
 * Created by zhouwb on 2018/10/15.
 */
@Service(
        interfaceClass = GenericPayService.class,
        validation = "true"     //启用参数验证(字段非空等)
)
public class PayServiceImpl implements GenericPayService {
    @Override
    public String pay() {
        return "你好，已经支付完毕！";
    }
}
