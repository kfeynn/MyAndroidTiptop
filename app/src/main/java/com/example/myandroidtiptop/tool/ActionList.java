package com.example.myandroidtiptop.tool;

/**
 * Created by Administrator on 2018/8/4.
 */

public  class ActionList {

    //public static final String UPDATE_URL = "http://192.168.0.63:9009/BarcodeWeb/update/gt_wms.apk";//正式安装包下载地址
    private static final String ACTION_SERVICE = "com.example.myandroidtiptop.service.";
    private static final String ACTION_SHOW = "com.example.myandroidtiptop.show.";
    public static final String action_boot ="android.intent.action.BOOT_COMPLETED"; //开机启动广播
    /**
     * Activity发送给Service 广播
     */
    public static final String ACTION_LOGIN = ACTION_SERVICE +"login";
    public static final String ACTION_QUERY_DNNUM = ACTION_SERVICE + "queryDn";
    public static final String ACTION_QUERY_PN_BY_BOXNUM = ACTION_SERVICE + "queryPnByBoxnum";
    public static final String ACTION_INPUT_RECEIVING = ACTION_SERVICE + "inputReceiving";
    public static final String ACTION_UPDATE = ACTION_SERVICE + "update";
    public static final String ACTION_QUERY_PKBOX_BY_PKNUM = ACTION_SERVICE + "queryPkboxByPknum";
    public static final String ACTION_INPUT_GROUNDING = ACTION_SERVICE + "inputGrounding";

    /***************************************************************************/

    /**
     * Service发送给Activity 广播
     */

    public static final String ACTION_SHOW_LOGIN = ACTION_SHOW + "login";
    public static final String ACTION_SHOW_PNSUBS = ACTION_SHOW + "Pnsubs";
    public static final String ACTION_SHOW_UPDATE_PNSUBS = ACTION_SHOW + "updatePnSubs";
    public static final String ACTION_SHOW_BOXLIST = ACTION_SHOW + "boxList";
    public static final String ACTION_SHOW_BOXWADDR = ACTION_SHOW +"boxWaddr";
    public static final String ACTION_SHOW_UPDATE = ACTION_SHOW + "update";
    public static final String ACTION_SHOW_LIST = ACTION_SHOW + "list";
    public static final String ACTION_SHOW_GoodsIptAndEptItem = ACTION_SHOW + "GoodsIptAndEptItem";
    public static final String ACTION_SHOW_LABEL = ACTION_SHOW + "label";

    /***************************************************************************/

    /**
     * scan data 接收广播
     */
    public static final String ACTION_QR_DATA_RECEIVED = "ACTION_QR_DATA_RECEIVED";
    //public static final String ACTION_QR_DATA_RECEIVED = "com.android.receive_scan_action";
    public static final int GET_PN_HANDLER_TYPE = 0;
    public static final int GET_LOGIN_HANDLER_TYPE = 1;
    public static final int GET_PN_BY_BOXNUM_HANDLER_TYPE = 2;
    public static final int Get_UPDATE_HANDLER_TYPE = 3;
    public static final int GET_CONFIRMRECEIVING_HANDLER_TYPE = 4;
    public static final int GET_CHANGEPASSWORD_HANDLER_TYPE = 5;
    public static final int GET_CONFIRMGROUNDING_HANDLER_TYPE = 6;
    public static final int GET_PK_BY_PKNUM_HANDLER_TYPE = 7;
    public static final int GET_CONFIRMPACKING_HANDLER_TYPE = 8;
    public static final int GET_BOX_BY_BOXNUM_HANDLER_TYPE = 9;
    public static final int GET_WOR_BY_WORNUM_HANDLER_TYPE = 10;
    public static final int GET_CONFIRMWORETURN_HANDLER_TYPE = 11;
    public static final int GET_CONFIRMIQC_HANDLER_TYPE = 12;
    public static final int GET_WHR_BY_WHNUM_HANDLER_TYPE = 13;
    public static final int GET_CONFIRMWHRETURN_HANDLER_TYPE = 14;
    public static final int GET_ITEM_BY_BOX_HANDLER_TYPE = 15;
    public static final int GET_CONFIRMGOODSMOVE_HANDLER_TYPE = 16;
    public static final int GET_IMGSBOX_BY_BOXNUM_HANDLER_TYPE = 17;
}
