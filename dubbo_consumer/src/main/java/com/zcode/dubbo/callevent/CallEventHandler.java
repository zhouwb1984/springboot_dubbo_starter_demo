package com.zcode.dubbo.callevent;

import com.zcode.dubbo.model.BillModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhouwb on 2018/10/15.
 */
@Component("callEventHandler")
public class CallEventHandler {

    Logger logger = LoggerFactory.getLogger(CallEventHandler.class);

    /**
     * 调用服务器count方法的回调
     * 指定参数
     * <dubbo:method name="count" onreturn="callEventHandler.countReturn"></>
     *
     * @param result
     *      服务器count方法的返回值
     *
     * @param date
     *      服务器count方法的参数
     *
     */
    public void countReturn(int result, String date){
        logger.info(Thread.currentThread().getName() + "-服务器count动作执行完毕，返回结果：" + date + "的单据量为：" + result);
    }

    public void findOneReturn(BillModel bill, String no){
        logger.info(Thread.currentThread().getName() + "-服务器findOne动作执行完毕，返回结果：" + no + "：" + bill.getNo() + "," + bill.getDate());
    }

}
