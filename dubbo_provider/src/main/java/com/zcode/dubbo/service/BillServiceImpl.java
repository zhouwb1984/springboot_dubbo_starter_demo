package com.zcode.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.zcode.dubbo.model.BillModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouwb on 2018/10/14.
 */
@Service(   //dubbo服务自动注册与暴露
        interfaceClass = BillService.class,
        validation = "true",    //启用参数验证(字段非空等)
        delay = -1  //spring启动完成后（延迟）暴露该服务
)
@Component
public class BillServiceImpl implements BillService {

    private Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

    @Override
    public List<BillModel> query() {
        logger.info("调用query()。。。");

        logger.info("获取从客户端传入的隐式参数：" + RpcContext.getContext().getAttachment("pwd"));

        List<BillModel> list = new ArrayList<>();
        BillModel bill = new BillModel();
        bill.setNo("133333333");
        bill.setDate("2018-11-12");
        list.add(bill);
        return list;
    }

    @Override
    public boolean save(BillModel billModel) {
        return true;
    }

    @Override
    public boolean updateData(BillModel billModel) {
        return true;
    }

    @Override
    public BillModel find() {
        BillModel billModel = new BillModel();
        billModel.setDate("1987");
        billModel.setNo("zx123");
        return billModel;
    }

    @Override
    public int count(String date) {
        return 10;
    }

    @Override
    public BillModel findOne(String no) {
        BillModel billModel = new BillModel();
        billModel.setDate("198745");
        billModel.setNo("zx123");
        return billModel;
    }

}
