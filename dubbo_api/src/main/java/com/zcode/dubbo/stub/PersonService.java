package com.zcode.dubbo.stub;

import com.zcode.dubbo.model.BillModel;

/**
 * Created by zhouwb on 2018/10/15.
 */
public interface PersonService {

    @interface FindOne{}
    PersonModel findOne(String no);

    @interface Save{}
    PersonModel save(PersonModel person);

}
