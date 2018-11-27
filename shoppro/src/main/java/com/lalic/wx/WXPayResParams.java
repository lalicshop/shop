package com.lalic.wx;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WXPayResParams {

    @XmlElement
    private String appid = WXConstant.getAppid();

    @XmlElement
    private String mch_id = WXConstant.getMch_id();

    @XmlElement
    private String out_trade_no;

    @XmlElement
    private String transaction_id;

    @XmlElement
    private String nonce_str = Utils.getNonce_str();

    @XmlElement
    private String sign;

    @XmlElement
    private String sign_type = "MD5";

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getAppid() {
        return appid;
    }

    public String getMch_id() {
        return mch_id;
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

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
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

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

}
