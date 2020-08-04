package com.example.myandroidtiptop.tool;

public class HttpPath {

    //后台服务 地址
    //private static final String IP = "http://192.168.0.63:9009/BarcodeWeb/app/";//正式服务器
    //private static final String IP = "http://192.168.14.11:8080/BarcodeWeb/app/";  //本地测试地址
    private static final String IP = "http://183.62.171.1:9009/BarcodeWeb/app/";   //外网服务地址

    //登录验证
    public static String getLoginPath() {
        return IP + "appLogin.do";
    }
    //获取送货单pn表数据
    public static String getDnMaterialListPath() {
        return IP + "appGetDnMaterialList.do";
    }
    //获取退货单pn表数据
    public static String getDnMaterialList1Path() {
        return IP + "appGetDnMaterialList1.do";
    }
    public static String getPNByBoxnumPath() {
        return IP + "appGetPnByBoxnum.do";
    }
    public static String getConfirmReceivingPath() {
        return IP + "receiving.do";
    }
    public static String getUpdateVersionPath() {
        return IP + "appGetUpdateVersion.do";
    }
    public static String getConfirmReturnPath() { return IP + "rejected.do"; }  //委外退货
    public static String getChangePwPath() {
        return IP + "appGetChangePwPath.do";
    }
    public static String getConfirmGroundingPath() {
        return IP + "grounding.do";
    }
    public static String appGetPKboxPath(){return  IP + "appGetPKbox.do";}
    public static String getConfirmPackingPath(){return  IP + "packing.do";}
    public static String appGetAKboxPath(){return  IP + "appGetAKbox.do";}      //厂内调拨查询请求
    public static String getConfirmAllotPath(){return  IP + "allot.do";}      //厂内调拨过账请求
    public static String appGetBKboxPath(){return  IP + "appGetBKbox.do";}      //报废查询请求
    public static String getConfirmDumpingPath(){return  IP + "dumping.do";}      //报废过账请求
    public static String appGetCKboxPath(){return  IP + "appGetCKbox.do";}      //拆解查询请求
    public static String getConfirmApartPath(){return  IP + "apart.do";}      //拆解过账请求
    public static String getBoxByBoxnumPath(){return  IP + "appGetBox.do";}   //收货扫描小条码
    public static String getBoxByBoxnum1Path(){return  IP + "appGetBox1.do";}   //退货扫描小条码
    public static String appGetWRboxPath(){return  IP + "appGetWRbox.do";}
    public static String getConfirmWoReturnPath(){return  IP + "woReturn.do";}
    public static String getConfirmIQCPath(){return  IP + "iqc.do";}
    public static String appGetWHRboxPath(){return  IP + "appGetWHRbox.do";}       //仓退资料查询
    public static String getConfirmWhReturnPath(){return  IP + "whReturn.do";}     //仓退单审核
    public static String appGetWHRboxaPath(){return  IP + "appGetWHRboxa.do";}       //仓退资料查询(交接)
    public static String getConfirmWhReturnJoinPath(){return  IP + "whReturnJoin.do";}     //仓退单交接
    public static String appGetRnboxaPath(){return  IP + "appGetRnboxa.do";}       //收货单资料查询(交接)
    public static String getConfirmReceivingJoin1Path(){return  IP + "receivingJoin1.do";}     //送货单单交接
    public static String appGetIptAndEptPath(){return  IP + "appGetImptAndEmptForm.do";}
    public static String appGetImgsBoxPath(){return  IP + "appGetImgsBox.do";}
    public static String getConfirmGoodsMovePath(){return  IP + "goodsMove.do";}
    public static String getIQCFormPath(){return  IP + "appGetIQCForm.do";}



}
