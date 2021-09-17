package com.zcode.dubbo.generic;

/**
 * 测试Dubbo泛化调用
 * 消费者端泛化调用服务端的该接口
 *
 * 因为客户端是泛化调用，所以在consumer工程里并没有该接口的引用，看不到该接口
 *
 * Created by zhouwb on 2018/10/15.
 */
public interface GenericPayService {

    String pay();

}
