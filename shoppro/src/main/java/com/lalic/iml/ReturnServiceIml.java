package com.lalic.iml;

import com.lalic.dao.DeliverDao;
import com.lalic.dao.OrderDao;
import com.lalic.dao.ProductDao;
import com.lalic.dao.ReturnDao;
import com.lalic.entity.DeliverModel;
import com.lalic.entity.OrderModel;
import com.lalic.entity.ProductModel;
import com.lalic.entity.ReturnModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.ReqConfirmRet;
import com.lalic.model.body.ReqMakeRet;
import com.lalic.model.body.ReqOrder;
import com.lalic.model.body.RetItemResp;
import com.lalic.service.ReturnService;
import com.lalic.util.Constant;
import com.lalic.util.TransferSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ReturnServiceIml implements ReturnService {

    @Autowired
    ReturnDao returnDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    DeliverDao deliverDao;


    @Override
    @Transactional
    public BaseResponse makereturn(ReqMakeRet makeRet) {

        Integer toRetCount = null;
        try {
            toRetCount = Integer.valueOf(makeRet.getCount());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new BaseResponse().setCode(400).setMess("输入错误");
        }

        if (toRetCount < 1) {
            return new BaseResponse().setCode(400).setMess("输入错误");
        }

        OrderModel order = null;
        try {
            order = orderDao.getOrderById(makeRet.getOrderid());
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResponse().setCode(400).setMess("输入错误");
        }
        if (order == null) {
            return new BaseResponse().setCode(403).setMess("非法操作");
        }
        if (!Constant.RENT.equals(order.getBuy_rent())) {
            return new BaseResponse().setCode(403).setMess("非法操作");
        }
        int canBeRetCount = Integer.valueOf(order.getQuantity()) - Integer.valueOf(order.getRetquantity());
        if (canBeRetCount < toRetCount) {
            return new BaseResponse().setCode(400).setMess("输入错误");
        }

        ReturnModel rm = new ReturnModel();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rm.setUserid(makeRet.getUserid());
        rm.setQuantity(makeRet.getCount());
        rm.setOrderid(makeRet.getOrderid());
        rm.setRetdate(sdf.format(new Date()));
        rm.setIsretmoney(Constant.IS_RETURN_MONEY_NO);
        rm.setStatus(Constant.ORDER_STATUS_RETURNING);
        rm.setDeliverno(makeRet.getDeliverno());
        OrderModel orderById = orderDao.getOrderById(makeRet.getOrderid());
        ProductModel productById = productDao.getProductById(orderById.getProductid());
        rm.setDescription(productById.getDetailname());
        rm.setMainpic(productById.getMainpic());
        rm.setName(makeRet.getName());
        rm.setPhone(makeRet.getPhone());
        returnDao.save(rm);

        orderDao.updateRetquantity(makeRet.getOrderid(), (toRetCount + Integer.valueOf(order.getRetquantity())) + "");
        return new BaseResponse();
    }

    @Override
    public List<ReturnModel> getAll(ReqOrder reqOrder) {
        List<ReturnModel> byUserid = returnDao.getByUserid(reqOrder.getUserid());
        return byUserid;
    }

    @Override
    @Transactional
    public BaseResponse confirmReturn(ReqConfirmRet makeRet) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        returnDao.confirmRet(makeRet.getMoney(), makeRet.getDeliverno(), sdf.format(new Date()));
        return new BaseResponse();
    }

    @Override
    @Transactional
    public BaseResponse getItem(ReqOrder reqOrder, String retid) {
        BaseResponse ret = new BaseResponse();
        Optional<ReturnModel> byId = returnDao.findById(retid);
        if (!byId.isPresent()) {
            return ret.setCode(403).setMess("非法操作");
        }
        ReturnModel returnModel = byId.get();
        if (!reqOrder.getUserid().equals(returnModel.getUserid())) {
            return ret.setCode(403).setMess("非法操作");
        }

        OrderModel order = orderDao.getOrderById(returnModel.getOrderid());

        ProductModel product = productDao.getProductById(order.getProductid());

        DeliverModel deliverByOrderId = deliverDao.getDeliverByOrderId(order.getOrderid());

        RetItemResp rit = new RetItemResp(returnModel, product.getProductid(), product.getMainpic(), product.getDetailname());
        Object o = TransferSearch.SearchById(deliverByOrderId.getCompany(), returnModel.getDeliverno());
        rit.setDeliverDetail(o);

        return ret.setData(rit);
    }

    @Override
    public BaseResponse returning(String deliverno) {
        ReturnModel byDeliverNo = returnDao.findByDeliverNo(deliverno);
        return new BaseResponse().setData(byDeliverNo);
    }

}
