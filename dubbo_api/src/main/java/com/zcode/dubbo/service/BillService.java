package com.zcode.dubbo.service;

import com.zcode.dubbo.model.BillModel;

import java.util.List;

/**
 * Created by zhouwb on 2018/10/14.
 */
public interface BillService {
    List<BillModel> query();

    /*
    参数校验用到注解Save，控制在保存时启用校验
    在BillMode类中有用到该注解
     */
    @interface Save{}
    boolean save(BillModel billModel);

    @interface UpdateData{}     //必须与方法同名才起作用，与顺序无关，首字符大写
    boolean updateData(BillModel billModel);

    BillModel find();

    int count(String date);

    BillModel findOne(String no);
}
