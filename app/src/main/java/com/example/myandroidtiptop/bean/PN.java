package com.example.myandroidtiptop.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 送货单头实体类
 * @author jiaquan.chen
 *
 */
public class PN implements Cloneable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 供应商编号 */
    private String supid;
    /** 供应商名称 */
    private String name;
    /** 供应商地址 */
    private String address;
    /** 供应商电话 */
    private String tel;
    /** 送货单号 */
    private String dnnum;
    /** 送货日期 */
    private String pmn33;
    /** 最近修改者 */
    private String change_user;
    /** 最近修改时间 */
    private String change_time;
    /** 状态 */
    private int status;
    /** 收货状态 */
    private int receiving_status;
    /** 运营中心 */
    private String plant;
    /** 收货单号*/
    private String rvb01;
    /** 报关单号*/
    private String importnumber;
    /** 交易类型*/
    private String importtype;

    private PN pn;

    private List<Box> boxs = new ArrayList<Box>();
    public List<PNSub> pnsubs = new ArrayList<PNSub>();
    public List<PNSub> pnSubs_copy = new ArrayList<PNSub>();
    public PN() {
        super();
    }
    public PN(String supid, String name, String address, String tel,
              String dnnum, String pmn33, String change_user, String change_time,
              int status, List<Box> boxs, List<PNSub> pnsubs, String plant) {
        super();
        this.supid = supid;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.dnnum = dnnum;
        this.pmn33 = pmn33;
        this.change_user = change_user;
        this.change_time = change_time;
        this.status = status;
        this.boxs = boxs;
        this.pnsubs = pnsubs;
        this.plant = plant;
    }
    public String getSupid() {
        return supid;
    }
    public void setSupid(String supid) {
        this.supid = supid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getDnnum() {
        return dnnum;
    }
    public void setDnnum(String dnnum) {
        this.dnnum = dnnum;
    }
    public String getPmn33() {
        return pmn33;
    }
    public void setPmn33(String pmn33) {
        if(pmn33 != null && !"".equals(pmn33) && pmn33.length() > 10)
            this.pmn33 = pmn33.substring(0, 10);
        else
            this.pmn33 = pmn33;
        //this.pmn33 = pmn33;
    }
    public String getChange_user() {
        return change_user;
    }
    public void setChange_user(String change_user) {
        this.change_user = change_user;
    }
    public String getChange_time() {
        return change_time;
    }
    public void setChange_time(String change_time) {
        this.change_time = change_time;
    }
    public List<Box> getBoxs() {
        return boxs;
    }
    public void setBoxs(List<Box> boxs) {
        this.boxs = boxs;
    }
    public List<PNSub> getPnsubs() {
        return pnsubs;
    }
    public void setPnsubs(List<PNSub> pnsubs) {
        this.pnsubs = pnsubs;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public int getReceiving_status() {
        return receiving_status;
    }

    public void setReceiving_status(int receiving_status) {
        this.receiving_status = receiving_status;
    }

    public String getRvb01() {
        return rvb01;
    }

    public void setRvb01(String rvb01) {
        this.rvb01 = rvb01;
    }

    public List<PNSub> getPnSubs_copy() {
        return pnSubs_copy;
    }

    public void setPnSubs_copy(List<PNSub> pnSubs_copy) {
        this.pnSubs_copy = pnSubs_copy;
    }

    public String getImportnumber() {
        return importnumber;
    }

    public void setImportnumber(String importnumber) {
        this.importnumber = importnumber;
    }

    public String getImporttype() {
        return importtype;
    }

    public void setImporttype(String importtype) {
        this.importtype = importtype;
    }

    public PN getPn() {
        pn = new PN();
        List<Box> boxs1 = new ArrayList<Box>();
        List<PNSub> pnsubs1 = new ArrayList<PNSub>();
        List<PNSub> pnSubs_copy1 = new ArrayList<PNSub>();
        for(PNSub pnSub : this.pnsubs){
            pnsubs1.add(pnSub);
        }
        for (Box box : this.boxs){
            boxs1.add(box);
        }
        for(PNSub pnSub : this.pnSubs_copy){
            pnSubs_copy1.add(pnSub);
        }
        pn.setPnsubs(pnsubs1);
        pn.setPnSubs_copy(pnSubs_copy1);
        pn.setSupid(supid);
        pn.setName(name);
        pn.setRvb01(rvb01);
        pn.setAddress(address);
        pn.setBoxs(boxs1);
        pn.setDnnum(dnnum);
        pn.setPlant(plant);
        pn.setPmn33(pmn33);
        pn.setReceiving_status(receiving_status);
        pn.setStatus(status);
        pn.setTel(tel);
        return pn;
    }

    public void setPn(PN pn) {
        this.pn = pn;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PN pn = null;
        pn = (PN)super.clone();
        return pn;
    }

    @Override
    public String toString() {
        return "PN [supid=" + supid + ", name=" + name + ", address=" + address
                + ", tel=" + tel + ", dnnum=" + dnnum + ", pmn33=" + pmn33
                + ", change_user=" + change_user + ", change_time="
                + change_time + ", status=" + status + ", boxs=" + boxs
                + ", pnsubs=" + pnsubs + "]";
    }
}
