package com.zcode.dubbo.model;

import com.zcode.dubbo.service.BillService;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by zhouwb on 2018/10/14.
 */
public class BillModel implements Serializable {

    @NotNull(groups={BillService.Save.class})   //save方法中，验证no不可空
    private String no;

    @NotNull(groups = {BillService.UpdateData.class})   //update方法中，验证date不可空。groups参数可以直接指定到类上BillService.class
    private String date;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
