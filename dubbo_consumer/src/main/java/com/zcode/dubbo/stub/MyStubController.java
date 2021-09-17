package com.zcode.dubbo.stub;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zcode.dubbo.model.BillModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 测试Stub（本地存根）
 *
 * Created by zhouwb on 2018/10/16.
 */
@RestController
public class MyStubController {

    /*
        服务端也配置了stub，定义了供所有消费者共用的stub类
        去掉注释，就是用这个服务端的stub
     */
//    @Reference
//    private PersonService personService;

    /*
        客户端配置stub，只给我一个消费者使用
        */
    @Reference(
            stub = "com.zcode.dubbo.stub.MyPersonServiceStub",  //本地存根
            mock = "com.zcode.dubbo.mock.MyPersonServiceMock"   //本地伪装
    )
    private PersonService personService;

    /**
     * 测试stub（本地存根）
     *
     * @return
     */
    @RequestMapping("/stubSave")
    public String stubSave(){
        PersonModel person = new PersonModel();
        person.setNo("999");      //服务端非空验证，注释掉可以让save抛错
        person.setName("你好");
        person.setAge(18);
        person = personService.save(person);
        return "client stub successfully! person : " + person.getName();
    }

    @RequestMapping("/stubFind")
    public String stubFind(){
        PersonModel person = personService.findOne("abc");
        String msg = null;
        if (person==null){  //测试本地伪装mock
            msg = "没有找到任何人";
        }else {
            msg = "找到了人员：" + person.getName();
        }
        return msg;
    }

}
