package com.lalic.wx;

import com.lalic.entity.OrderModel;
import com.lalic.entity.ProductModel;
import com.lalic.http.SimpleHttp;
import com.lalic.model.body.MakeOrderResp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WXPay {

    private static final Logger logger = LoggerFactory.getLogger(WXPay.class);

    public static String makeWXPay(MakeOrderResp mess, OrderModel order, ProductModel product) {

        WXPayParams pay = new WXPayParams();
        pay.setBody(product.getShortname());
        pay.setOut_trade_no(order.getOrderid());
        pay.setTotal_fee(Integer.valueOf(mess.getTotalPrice()) * 100);
        pay.setSpbill_create_ip(WXConstant.getLocalIP());
        pay.setNotify_url("http://www.baidu.com");
        pay.setOpenid("ouccJ4y88qLWUFv_PKdka80zJv6Q");
        pay.setSign(Utils.sign(pay));

        String postBody = Utils.makeXML(pay);

        if (Utils.isEmpty(postBody)) {
            return "";
        }

        String wxresp = SimpleHttp.post(WXConstant.getMakeorderurl(), null, postBody).getBody();
        System.out.println(wxresp);
        WXPayResp wxPayResp = Utils.parseXML(wxresp);
        if (wxPayResp == null) {
            return "";
        }
        if ("SUCCESS".equals(wxPayResp.getReturn_code()) && "SUCCESS".equals(wxPayResp.getResult_code())) {
            logger.info("prepayid = " + wxPayResp.getPrepay_id());
            return wxPayResp.getPrepay_id();
        }
        return "";
    }

    public static void main(String[] args) {
        WXPayResp wxPayResp = Utils.parseXML("<xml><return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<return_msg><![CDATA[OK]]></return_msg>\n" +
                "<appid><![CDATA[wx430252612d3607bc]]></appid>\n" +
                "<mch_id><![CDATA[1519177151]]></mch_id>\n" +
                "<nonce_str><![CDATA[T0hSIFY6XDPP4lrX]]></nonce_str>\n" +
                "<sign><![CDATA[805CC4F11F0B30B052175856BEF02E97]]></sign>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<prepay_id><![CDATA[wx26210000399437b31115ca7f1571110942]]></prepay_id>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "</xml>");

        System.out.println("");
    }


}
