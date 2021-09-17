package com.zcode.dubbo.mock;

import com.zcode.dubbo.stub.PersonModel;
import com.zcode.dubbo.stub.PersonService;

/**
 * Mock，本地伪装
 *
 * 当调用远程服务失败时（抛RpcException），执行本伪装类中的代码，return null 忽略异常
 *
 * Created by zhouwb on 2018/10/16.
 */
public class MyPersonServiceMock implements PersonService {
    @Override
    public PersonModel findOne(String no) {
        return null;
    }

    @Override
    public PersonModel save(PersonModel person) {
        return null;
    }
}
