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

    public static String signPay(WXPayParams params) {

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

    public static String signPayRes(WXPayResParams params) {

        TreeMap paramMap = new TreeMap();
        paramMap.put("appid", params.getAppid());//公众账号ID
        paramMap.put("mch_id", params.getMch_id());//商户号
        paramMap.put("nonce_str", params.getNonce_str());//随机字符串
        paramMap.put("sign_type", params.getSign_type());//签名类型
        paramMap.put("out_trade_no", params.getOut_trade_no());//商户订单号
        paramMap.put("sign", params.getSign());

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
        System.out.println("签名：" + sb.toString());
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

    public static String makeXML(Object obj, Class clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> T parseXML(String xmlstr, Class<T> clazz) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            T resp = (T) unmarshaller.unmarshal(new StringReader(xmlstr));
            return resp;

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }


    public static String signweb(WXPayWeb params) {

        TreeMap paramMap = new TreeMap();
        paramMap.put("nonceStr", params.getNonceStr());//公众账号ID
        paramMap.put("package", params.getPackage_());//商户号
        paramMap.put("signType", "MD5");//签名类型
        paramMap.put("appId", WXConstant.getAppid());
        paramMap.put("timeStamp", params.getTimeStamp());

        return createSign(paramMap);
    }

    public static void main(String[] args) {

        WXPayWeb wxPayWeb = new WXPayWeb();
        System.out.println(signweb(wxPayWeb));

    }

}
