package com.zcode.dubbo.callback;

/**
 * Created by zhouwb on 2018/10/15.
 */
public interface VoucherService {
    void findVoucher(String id, CallbackHandler cb);
}
