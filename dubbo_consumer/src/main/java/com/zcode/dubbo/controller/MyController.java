package com.zcode.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.zcode.dubbo.callback.BeanWraper;
import com.zcode.dubbo.callback.CallbackHandler;
import com.zcode.dubbo.model.BillModel;
import com.zcode.dubbo.service.BillService;
import com.zcode.dubbo.callback.VoucherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by zhouwb on 2018/10/14.
 */
@RestController
public class MyController {

    private static Logger logger = LoggerFactory.getLogger(MyController.class);

    /*
    注解引用dubbo服务
     */
    @Reference(
            cache = "lru",  //启用缓存
            parameters = {
                    "find.async", "true"    //指示find()方法为异步调用<dubbo.method name="find" async="true" />
            }
    )
    private BillService billService;

    @Reference
    private VoucherService voucherService;

    @RequestMapping("/billList")
    public String billList(){

        //向服务器隐式传递参数
        RpcContext.getContext().setAttachment("pwd", "zhouwb@123.com");

        List<BillModel> list = billService.query();
        String s = "";
        for (BillModel bill:list){
            s += bill.getNo() + "," + bill.getDate();
        }
        logger.info("订单：" + s);
        return s;
    }

    @RequestMapping("/")
    public String index(){
        return "hello";
    }

    /**
     * 该方法主要用来测试Dubbo与参数验证功能的结合
     *
     * billService开启了参数验证功能，方式有两种
     * 1、在服务提供者中，暴露该服务时，开启；这将在服务端执行验证
     * 2、在消费者中，引用该服务的时候，开启；这将在客户端执行验证
     *
     * @return
     */
    @RequestMapping("/save")
    public String save(){
        boolean success = true;
        try {
            BillModel bill = new BillModel();
            //bill.setNo("12345");    //注释掉这行，就会触发校验失败。
            bill.setDate("2019-01-01");
            success = billService.save(bill);
        }catch (Exception e){
            logger.error("参数校验失败！", e);
            success = false;
        }
        return  success ? "保存成功" : "failed";
    }

    @RequestMapping("/update")
    public String update(){
        boolean success = true;
        try {
            BillModel bill = new BillModel();
            bill.setNo("12345");
            //bill.setDate("2019-01-01");   //注释掉这行，就会触发校验失败。
            success = billService.updateData(bill);
        }catch (Exception e){
            logger.error("参数校验失败！", e);
            success = false;
        }
        return  success ? "更新成功" : "failed";
    }

    /**
     * 回声测试
     *
     * 用于测试远程服务接口是否可用。使用Dubbo的EchoService接口
     *
     * @return
     */
    @RequestMapping("/echo")
    public String $echo(){
        EchoService service = (EchoService)billService;
        String status = (String)service.$echo("OK");
        logger.info(status);
        return status;
    }

    /**
     * RPC上下文
     * @return
     */
    @RequestMapping("/context")
    public String context(){

        RpcContext context = RpcContext.getContext();

        //调用一次远程服务后，才会产生RPC上下文信息
        $echo();

        logger.info("本地是否为消费端：" + context.isConsumerSide());

        logger.info("最后一次调用的远端IP:" + context.getRemoteAddressString());

        logger.info(context.getUrl().getParameter("application"));

        return context.getLocalAddress().getHostString();
    }

    /**
     * 异步调用远程服务
     * @return
     */
    @RequestMapping("/async")
    public String async(){

        //异步调用
        billService.find();
        Future<BillModel> future = RpcContext.getContext().getFuture();

        BillModel bill = null;
        try {
            bill = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        logger.info("异步调用find:" + bill.getNo() + "," + bill.getDate());
        return bill.getDate();
    }

    /**
     * 客户端参数传递的方式，服务器回调客户端
     * @return
     */
    @RequestMapping("/callback")
    public String callback(){
        CountDownLatch latch = new CountDownLatch(1);
        final BeanWraper<BillModel> rs = new BeanWraper<>();
        voucherService.findVoucher("123", new CallbackHandler() {
            @Override
            public void found(BillModel billModel) {
                logger.info(Thread.currentThread().getName() + "-查询到单据："
                        + billModel.getNo() + "," + billModel.getDate());
                rs.set(billModel);
                latch.countDown();
            }
        });
        try {
            latch.await();  //同步等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info(Thread.currentThread().getName() + "查询单据完成");
        return rs.get().getNo();
    }

}
