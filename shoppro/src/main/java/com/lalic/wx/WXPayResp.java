package com.lalic.wx;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WXPayResp {

    @XmlElement
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    private String return_code;

    @XmlElement
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    private String return_msg;

    @XmlElement
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    private String appid;

    @XmlElement
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    private String mch_id;

    @XmlElement
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    private String nonce_str;

    @XmlElement
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    private String openid;

    @XmlElement
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    private String sign;

    @XmlElement
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    private String result_code;

    @XmlElement
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    private String prepay_id;

    @XmlElement
    @XmlJavaTypeAdapter(value = CDATAAdapter.class)
    private String trade_type;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
}
