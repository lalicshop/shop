package com.lalic.service;

import com.lalic.entity.ReturnModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.ReqConfirmRet;
import com.lalic.model.body.ReqMakeRet;
import com.lalic.model.body.ReqOrder;

import java.util.List;

public interface ReturnService {

    BaseResponse makereturn(ReqMakeRet makeRet);

    List<ReturnModel> getAll(ReqOrder reqOrder);

    BaseResponse confirmReturn(ReqConfirmRet makeRet);

    BaseResponse getItem(ReqOrder reqOrder, String retid);

    BaseResponse returning(String deliverno);
}
