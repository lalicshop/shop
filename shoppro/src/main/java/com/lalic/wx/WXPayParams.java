package com.lalic.wx;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WXPayParams {

    @XmlElement
    private String appid = WXConstant.getAppid();

    @XmlElement
    private String mch_id = WXConstant.getMch_id();

    @XmlElement
    private String device_info;

    @XmlElement
    private String nonce_str = Utils.getNonce_str();

    @XmlElement
    private String sign;

    @XmlElement
    private String sign_type = "MD5";

    @XmlElement
    private String body;

    @XmlElement
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    private String detail;

    @XmlElement
    private String attach;

    @XmlElement
    private String out_trade_no;

    @XmlElement
    private String fee_type;

    @XmlElement
    private int total_fee;

    @XmlElement
    private String spbill_create_ip;

    @XmlElement
    private String time_start;

    @XmlElement
    private String time_expire;

    @XmlElement
    private String goods_tag;

    @XmlElement
    private String notify_url;

    @XmlElement
    private String trade_type = "JSAPI";

    @XmlElement
    private String product_id;

    @XmlElement
    private String limit_pay;

    @XmlElement
    private String openid;

    public String getAppid() {
        return appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public String getSign_type() {
        return sign_type;
    }

    public String getBody() {
        return body;
    }

    public String getDetail() {
        return detail;
    }

    public String getAttach() {
        return attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public String getFee_type() {
        return fee_type;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public String getTime_start() {
        return time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
