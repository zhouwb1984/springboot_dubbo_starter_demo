package com.zcode.dubbo.stub;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by zhouwb on 2018/10/15.
 */
public class PersonModel implements Serializable {

    @NotNull(groups = {PersonService.Save.class})   //no保存时不允许为空
    private String no;
    @NotNull(groups = {PersonService.FindOne.class})
    private String name;
    private int age;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
