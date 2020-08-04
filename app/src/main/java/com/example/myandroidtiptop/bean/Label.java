package com.example.myandroidtiptop.bean;

import java.io.Serializable;

/**
 * 内标签实体类
 * @author jiaquan.chen
 *
 */
public class Label implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 料号 */
    private String rvb05;
    /** 数量 */
    private String qty;
    /** 内标签号 */
    private String labelId;
    /** 状态 */
    private int status;

    public String getRvb05() {
        return rvb05;
    }

    public void setRvb05(String rvb05) {
        this.rvb05 = rvb05;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
