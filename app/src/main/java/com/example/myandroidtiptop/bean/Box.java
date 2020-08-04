package com.example.myandroidtiptop.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 包装票实体类
 * @author jiaquan.chen
 *
 */
public class Box implements Serializable,Cloneable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 采购单号 */
    private String pmm01;
    /** 项次 */
    private int pmn02;
    /** 批次 */
    private String lot;
    /** 供应商 */
    private String pmc01;
    /** 物料编号 */
    private String pmn04;
    /** 采购数量 */
    private double pmn20;
    /** 送货日期 */
    private String pmn33;
    /** 品名 */
    private String pmn041;
    /** 状态 */
    private int status;
    /** 箱号 */
    private String boxnum;

    /** 实收数量 */
    private int rvb07;
    /** 检验码  */
    private String ima24;
    /** 是否检验，N代表免检*/
    private String rvb39;
    /** 检验退数量 */
    private double rvb29;
    /** 入库数量 */
    private double rvb30;
    /** 允许入库数量 */
    private double rvb33;
    /** 已入库数量 */
    private double rvb31;
    /**收货单项次*/
    private int rvb02;
    /** 单个条码收货数量 */
    private double ibb07;
    /**审核码,Y代表已审核*/
    private String qcs14;
    /**
     * 标记是否被扫描
     */
    private boolean ifScanedBox = false;

    /** 仓库 */
    private String rvv32 = "";
    /** 储位 */
    private String rvv33 = "";

    private PN pn;
    private List<Label> maters = new ArrayList<Label>();

    public Box() {
        super();
    }
    public Box(String pmm01, int pmn02, String lot, String pmc01, String pmn04,
               double pmn20, String pmn33, String pmn041, int status,
               String boxnum, PN pn, List<Label> maters) {
        super();
        this.pmm01 = pmm01;
        this.pmn02 = pmn02;
        this.lot = lot;
        this.pmc01 = pmc01;
        this.pmn04 = pmn04;
        this.pmn20 = pmn20;
        this.pmn33 = pmn33;
        this.pmn041 = pmn041;
        this.status = status;
        this.boxnum = boxnum;
        this.pn = pn;
        this.maters = maters;
    }
    public String getPmm01() {
        return pmm01;
    }
    public void setPmm01(String pmm01) {
        this.pmm01 = pmm01;
    }
    public int getPmn02() {
        return pmn02;
    }
    public void setPmn02(int pmn02) {
        this.pmn02 = pmn02;
    }
    public String getLot() {
        return lot;
    }
    public void setLot(String lot) {
        this.lot = lot;
    }
    public String getPmc01() {
        return pmc01;
    }
    public void setPmc01(String pmc01) {
        this.pmc01 = pmc01;
    }
    public String getPmn04() {
        return pmn04;
    }
    public void setPmn04(String pmn04) {
        this.pmn04 = pmn04;
    }
    public double getPmn20() {
        return pmn20;
    }
    public void setPmn20(double pmn20) {
        this.pmn20 = pmn20;
    }
    public String getPmn33() {
        return pmn33;
    }
    public void setPmn33(String pmn33) {
        this.pmn33 = pmn33;
    }
    public String getPmn041() {
        return pmn041;
    }
    public void setPmn041(String pmn041) {
        this.pmn041 = pmn041;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getBoxnum() {
        return boxnum;
    }
    public void setBoxnum(String boxnum) {
        this.boxnum = boxnum;
    }
    public PN getPn() {
        return pn;
    }
    public void setPn(PN pn) {
        this.pn = pn;
    }
    public List<Label> getMaters() {
        return maters;
    }
    public void setMaters(List<Label> maters) {
        this.maters = maters;
    }

    public boolean isIfScanedBox() {
        return ifScanedBox;
    }

    public void setIfScanedBox(boolean ifScanedBox) {
        this.ifScanedBox = ifScanedBox;
    }

    public int getRvb07() {
        return rvb07;
    }

    public void setRvb07(int rvb07) {
        this.rvb07 = rvb07;
    }

    public String getIma24() {
        return ima24;
    }

    public void setIma24(String ima24) {
        this.ima24 = ima24;
    }

    public String getRvb39() {
        return rvb39;
    }

    public void setRvb39(String rvb39) {
        this.rvb39 = rvb39;
    }

    public double getRvb29() {
        return rvb29;
    }

    public void setRvb29(double rvb29) {
        this.rvb29 = rvb29;
    }

    public double getRvb30() {
        return rvb30;
    }

    public void setRvb30(double rvb30) {
        this.rvb30 = rvb30;
    }

    public double getRvb33() {
        return rvb33;
    }

    public void setRvb33(double rvb33) {
        this.rvb33 = rvb33;
    }

    public String getRvv32() {
        return rvv32;
    }

    public void setRvv32(String rvv32) {
        this.rvv32 = rvv32;
    }

    public String getRvv33() {
        return rvv33;
    }

    public void setRvv33(String rvv33) {
        this.rvv33 = rvv33;
    }

    public double getRvb31() {
        return rvb31;
    }

    public void setRvb31(double rvb31) {
        this.rvb31 = rvb31;
    }

    public int getRvb02() {
        return rvb02;
    }

    public void setRvb02(int rvb02) {
        this.rvb02 = rvb02;
    }

    public double getIbb07() {
        return ibb07;
    }

    public void setIbb07(double ibb07) {
        this.ibb07 = ibb07;
    }

    public String getQcs14() {
        return qcs14;
    }

    public void setQcs14(String qcs14) {
        this.qcs14 = qcs14;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Box box = null;
        return (Box)super.clone();
    }

    @Override
    public String toString() {
        return "Box [pmm01=" + pmm01 + ", pmn02=" + pmn02 + ", lot=" + lot
                + ", pmc01=" + pmc01 + ", pmn04=" + pmn04 + ", pmn20=" + pmn20
                + ", pmn33=" + pmn33 + ", pmn041=" + pmn041 + ", status="
                + status + ", boxnum=" + boxnum + ", pn=" + pn + ", maters="
                + maters + "]";
    }
}

