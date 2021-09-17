package com.zcode.dubbo.callback;

import com.zcode.dubbo.model.BillModel;

/**
 * 参数回调处理器
 * Created by zhouwb on 2018/10/15.
 */
public interface CallbackHandler {
    void found(BillModel billModel);
}
