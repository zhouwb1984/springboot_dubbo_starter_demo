package com.zcode.dubbo.stub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务的stub实现，这些代码将会在客户端执行
 *
 * Created by zhouwb on 2018/10/15.
 */
public class PersonServiceStub implements PersonService {

    Logger logger = LoggerFactory.getLogger(PersonServiceStub.class);

    //这是真正的服务提供者
    private PersonService _service;

    public PersonServiceStub(PersonService service){
        this._service = service;
    }

    public PersonModel findOne(String no) {

        logger.info("线程【" + Thread.currentThread().getName() + "】执行方法【findOne】，我在客户端执行");

        if (no==null){
            PersonModel person = new PersonModel();
            person.setNo("000");
            person.setName("No Body!");
            person.setAge(-1);
            return person;
        }
        //调用真正的服务提供者
        return _service.findOne(no);
    }

    public PersonModel save(PersonModel person) {

        logger.info("线程【" + Thread.currentThread().getName() + "】执行方法【save】，我在客户端执行");

        try{
            return _service.save(person);
        }catch (Exception e){
            logger.error("保存人员失败！", e);
            person = new PersonModel();
            person.setNo("000");
            person.setName("No Body!");
            person.setAge(-1);
            return person;
        }
    }
}
