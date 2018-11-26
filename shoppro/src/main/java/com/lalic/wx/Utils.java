package com.lalic.wx;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Utils {

    public static String getNonce_str() {
        return UUID.randomUUID().toString().replace("-", "").substring(16);
    }

    public static String sign(WXPayParams params) {

        TreeMap paramMap = new TreeMap();
        paramMap.put("appid", params.getAppid());//公众账号ID
        paramMap.put("mch_id", params.getMch_id());//商户号
        paramMap.put("nonce_str", params.getNonce_str());//随机字符串
        paramMap.put("sign_type", params.getSign_type());//签名类型
        paramMap.put("body", params.getBody());//商品描述
        paramMap.put("out_trade_no", params.getOut_trade_no());//商户订单号
        paramMap.put("total_fee", params.getTotal_fee());//标价金额
        paramMap.put("spbill_create_ip", params.getSpbill_create_ip());//终端IP
        paramMap.put("notify_url", params.getNotify_url());//通知地址
        paramMap.put("trade_type", params.getTrade_type());//交易类型
        paramMap.put("device_info", params.getDevice_info());//用户标识
        paramMap.put("sign", params.getSign());
        paramMap.put("detail", params.getDetail());
        paramMap.put("attach", params.getAttach());
        paramMap.put("fee_type", params.getFee_type());
        paramMap.put("time_start", params.getTime_start());
        paramMap.put("time_expire", params.getTime_expire());
        paramMap.put("goods_tag", params.getGoods_tag());
        paramMap.put("product_id", params.getProduct_id());
        paramMap.put("limit_pay", params.getLimit_pay());
        paramMap.put("openid", params.getOpenid());

        return createSign(paramMap);
    }

    public static String createSign(SortedMap<String, Object> parameters) {
        StringBuilder sb = new StringBuilder();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k).append("=").append(v).append("&");
            }
        }
        sb.append("key=").append(WXConstant.getKey());
//        System.out.println("签名：" + sb.toString());
        return stringToMD5(sb.toString()).toUpperCase();
    }

    public static String stringToMD5(String plainText) {
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    plainText.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static String makeXML(WXPayParams params) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WXPayParams.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(params, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static WXPayResp parseXML(String xmlstr) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(WXPayResp.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            WXPayResp resp = (WXPayResp) unmarshaller.unmarshal(new StringReader(xmlstr));
            return resp;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static void main(String[] args) {
        WXPayResp resp = parseXML("<xml>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[IITRi8Iabbblz1Jc]]></nonce_str>\n" +
                "   <openid><![CDATA[oUpF8uMuAJO_M2pxb1Q9zNjWeS6o]]></openid>\n" +
                "   <sign><![CDATA[7921E432F65EB8ED0CE9755F0E86D72F]]></sign>\n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>\n" +
                "   <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "</xml>");
        System.out.println("");
    }

}
