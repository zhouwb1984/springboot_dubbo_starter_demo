package com.zcode.dubbo.stub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhouwb on 2018/10/16.
 */
public class MyPersonServiceStub implements PersonService {

    private static Logger logger = LoggerFactory.getLogger(MyPersonServiceStub.class);

    //真正的服务提供者
    private PersonService _service;

    public MyPersonServiceStub(PersonService service){
        this._service = service;
    }

    @Override
    public PersonModel findOne(String no) {
        logger.info("准备执行保存人员动作，我是消费者，我要做一些自己的准备。。。");
        PersonModel person = _service.findOne(no);
        if(person!=null){
            logger.info("person : " + person.getName());
        }
        logger.info("服务端完成保存人员动作，我是消费者，我要做一些自己的特殊处理。。。");
        return person;
    }

    @Override
    public PersonModel save(PersonModel person) {
        logger.info("准备执行保存人员动作，我是消费者，我要做一些自己的准备。。。");
        person = _service.save(person);
        if(person!=null){
            logger.info("person : " + person.getName());
        }
        logger.info("服务端完成保存人员动作，我是消费者，我要做一些自己的特殊处理。。。");
        return person;
    }
}
