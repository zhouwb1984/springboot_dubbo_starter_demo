package com.zcode.dubbo.callback;

import com.zcode.dubbo.model.BillModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 参数回调
 *
 * Created by zhouwb on 2018/10/15.
 */
public class VoucherServiceImpl implements VoucherService {

    //客户端回调任务集合
    private final Map<String, CallbackHandler> listeners = new ConcurrentHashMap<>();

    public VoucherServiceImpl(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){   //轮询
                    for (Map.Entry<String, CallbackHandler> entry :
                            listeners.entrySet()){
                        listeners.entrySet().remove(entry);
                        BillModel bill = find(entry.getKey());  //查询数据
                        entry.getValue().found(bill);
                    }
                    try {
                        Thread.sleep(5000); //5秒轮询一次
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true); //守护线程
        thread.start();
    }

    @Override
    public void findVoucher(String id, CallbackHandler cb) {
        listeners.put(id, cb);
    }

    private BillModel find(String id){
        BillModel bill = new BillModel();
        bill.setNo("3452");
        bill.setDate("1999");
        return bill;
    }
}
