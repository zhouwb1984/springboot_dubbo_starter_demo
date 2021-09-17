package com.zcode.dubbo.stub;


import com.alibaba.dubbo.config.annotation.Service;

/**
 * 服务的提供者
 *
 * Created by zhouwb on 2018/10/15.
 */

/*
    在服务端配置stub，所有调用该服务的客户端都使用该stub
    当然，也可以在消费者程序中配置stub参数（@Reference），只能给自己（消费者）使用。
 */
@Service(
        stub = "com.zcode.dubbo.stub.PersonServiceStub",
        validation = "true")
public class PersonServiceImpl implements PersonService {

    @Override
    public PersonModel findOne(String no) {
        PersonModel person = new PersonModel();
        person.setNo("123");
        person.setName("龙文章");
        person.setAge(35);
        return person;
    }

    @Override
    public PersonModel save(PersonModel person) {
        return person;
    }

}
