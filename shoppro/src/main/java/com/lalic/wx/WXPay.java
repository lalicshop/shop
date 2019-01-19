package com.lalic.wx;

import com.lalic.entity.ProductModel;
import com.lalic.http.SimpleHttp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WXPay {

    private static final Logger logger = LoggerFactory.getLogger(WXPay.class);

    public static String makeWXPay(String orderid, String totalprice, String outtradeid, String userid, ProductModel product) {

        WXPayParams pay = new WXPayParams();
        pay.setBody(product.getShortname());
        pay.setOut_trade_no(outtradeid);
        pay.setTotal_fee(Integer.valueOf(totalprice));
        pay.setSpbill_create_ip(WXConstant.getLocalIP());
        pay.setNotify_url("https://www.senderkong.com/order/confirmpay/" + orderid);
        pay.setOpenid(userid);
        pay.setSign(Utils.signPay(pay));

        String postBody = Utils.makeXML(pay, WXPayParams.class);

        if (Utils.isEmpty(postBody)) {
            return "";
        }

        String wxresp = SimpleHttp.post(WXConstant.getMakeorderurl(), null, postBody).getBody();
        System.out.println(wxresp);
        WXPayResp wxPayResp = Utils.parseXML(wxresp, WXPayResp.class);
        if (wxPayResp == null) {
            return "";
        }
        if ("SUCCESS".equals(wxPayResp.getReturn_code()) && "SUCCESS".equals(wxPayResp.getResult_code())) {
            logger.info("prepayid = " + wxPayResp.getPrepay_id());
            return wxPayResp.getPrepay_id();
        }
        return "";
    }

    public static WXPayResResp searchPayRes(String orderid) {

        WXPayResParams params = new WXPayResParams();
        params.setOut_trade_no(orderid);
        params.setSign(Utils.signPayRes(params));

        String postBody = Utils.makeXML(params, WXPayResParams.class);
        System.out.println(postBody);
        SimpleHttp.Response post = SimpleHttp.post(WXConstant.getQueryorderurl(), null, postBody);

        if (post == null) {
            return null;
        }
        return Utils.parseXML(post.getBody(), WXPayResResp.class);
    }

    public static void main(String[] args) {


    }


}
