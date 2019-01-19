package com.lalic.iml;

import com.lalic.dao.AddressDao;
import com.lalic.dao.CartDao;
import com.lalic.dao.DeliverDao;
import com.lalic.dao.OrderDao;
import com.lalic.dao.OrderDetailDao;
import com.lalic.dao.ProductDao;
import com.lalic.entity.AddressModel;
import com.lalic.entity.CartModel;
import com.lalic.entity.DeliverModel;
import com.lalic.entity.OrderModel;
import com.lalic.entity.ProductModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.AllOrderResp;
import com.lalic.model.body.DeliverResp;
import com.lalic.model.body.MakeOrderResp;
import com.lalic.model.body.NotDeliver;
import com.lalic.model.body.ReqConfirmOrder;
import com.lalic.model.body.ReqDeliverOrder;
import com.lalic.model.body.ReqMakeOrder;
import com.lalic.model.body.ReqMakeWaitOrder;
import com.lalic.model.body.ReqRemoveOrder;
import com.lalic.model.body.ReturnableResp;
import com.lalic.service.OrderService;
import com.lalic.util.BuyRentMapping;
import com.lalic.util.Constant;
import com.lalic.util.DeliverPriceMapping;
import com.lalic.util.OrderStatusMapping;
import com.lalic.util.TransferSearch;
import com.lalic.util.ZhDeliverComMapping;
import com.lalic.wx.Utils;
import com.lalic.wx.WXConstant;
import com.lalic.wx.WXPay;
import com.lalic.wx.WXPayResResp;
import com.lalic.wx.WXPayWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class OrderServiceIml implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderDetailDao orderDetailDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    DeliverDao deliverDao;

    @Autowired
    CartDao cartDao;

    @Autowired
    AddressDao addressDao;

    @Override
    public OrderModel getOrderById(String orderid) {
        return orderDao.getOrderById(orderid);
    }

    @Override
    public AllOrderResp getOrderByUserid(String userid) {
        AllOrderResp ret = new AllOrderResp();
        List<OrderModel> allOrders = orderDao.getOrderByUserid(userid);
        if (allOrders.size() == 0) {
            return ret;
        }

        AllOrderResp.OrderItem orderWtPay = new AllOrderResp.OrderItem();
        orderWtPay.setId(Constant.ORDER_STATUS_WAITFORPAY);
        orderWtPay.setTag(OrderStatusMapping.getStatus(Constant.ORDER_STATUS_WAITFORPAY));

        AllOrderResp.OrderItem orderPaid = new AllOrderResp.OrderItem();
        orderPaid.setId(Constant.ORDER_STATUS_PAID);
        orderPaid.setTag(OrderStatusMapping.getStatus(Constant.ORDER_STATUS_PAID));

        AllOrderResp.OrderItem orderDeIng = new AllOrderResp.OrderItem();
        orderDeIng.setId(Constant.ORDER_STATUS_DELIVERING);
        orderDeIng.setTag(OrderStatusMapping.getStatus(Constant.ORDER_STATUS_DELIVERING));

        AllOrderResp.OrderItem orderFinish = new AllOrderResp.OrderItem();
        orderFinish.setId(Constant.ORDER_STATUS_DELIVERED);
        orderFinish.setTag(OrderStatusMapping.getStatus(Constant.ORDER_STATUS_DELIVERED));


        for (OrderModel order : allOrders) {
            ProductModel productById = productDao.getProductById(order.getProductid());
            AllOrderResp.OrderItem.InnerItem item = new AllOrderResp.OrderItem.InnerItem();
            item.setDate(order.getGeneratedate());
            item.setDescription(productById.getDetailname());
            item.setId(order.getOrderid());
            item.setImage_url(productById.getMainpic());
            switch (order.getStatus()) {
                case Constant.ORDER_STATUS_WAITFORPAY:
                    item.setStatus(OrderStatusMapping.getStatus(Constant.ORDER_STATUS_WAITFORPAY));
                    orderWtPay.addItem(item);
                    break;
                case Constant.ORDER_STATUS_PAID:
                    item.setStatus(OrderStatusMapping.getStatus(Constant.ORDER_STATUS_PAID));
                    orderPaid.addItem(item);
                    break;
                case Constant.ORDER_STATUS_DELIVERING:
                    item.setStatus(OrderStatusMapping.getStatus(Constant.ORDER_STATUS_DELIVERING));
                    orderDeIng.addItem(item);
                    break;
                case Constant.ORDER_STATUS_DELIVERED:
                    item.setStatus(OrderStatusMapping.getStatus(Constant.ORDER_STATUS_DELIVERED));
                    orderFinish.addItem(item);
                    break;
            }
        }
        ret.addItem(orderWtPay);
        ret.addItem(orderPaid);
        ret.addItem(orderDeIng);
        ret.addItem(orderFinish);
        return ret;
    }

    @Override
    @Transactional
    public BaseResponse makeOrder(ReqMakeOrder makeOrder) {
        CartModel cartById = cartDao.getCartById(makeOrder.getCartId());

        if (cartById == null) {
            return new BaseResponse().setMess("非法操作").setCode(400);
        }

        if (!"-1".equals(makeOrder.getCartId())) {
            //-1代表是从未支付订单列表跳过来支付的，不是从购物车过来的
            if (cartDao.existsById(makeOrder.getCartId())) {
                cartDao.deleteById(makeOrder.getCartId());
            } else {
                return new BaseResponse().setMess("非法操作").setCode(403);
            }
        }

        String productid = cartById.getProductid();
        String count = cartById.getProductcount();
        ProductModel product = productDao.getProductById(productid);
        int price = 0;
        if (Constant.BUY.equalsIgnoreCase(cartById.getBuyrent())) {
            price = Double.valueOf(product.getBuyprice()).intValue();
        } else if (Constant.RENT.equalsIgnoreCase(cartById.getBuyrent())) {
            price = Double.valueOf(product.getRentprice()).intValue();
        }
        price = price * Integer.valueOf(count);
        OrderModel order = new OrderModel();
        order.setWaitpayid(order.getOrderid());
        order.setBuy_rent(cartById.getBuyrent());
        order.setProductid(cartById.getProductid());
        order.setQuantity(cartById.getProductcount());
        order.setCm(makeOrder.getCm());
        order.setKg(makeOrder.getKg());
        order.setTotalprice(price);
        order.setStatus(Constant.ORDER_STATUS_WAITFORPAY);
        order.setRetquantity("0");
        order.setUserid(makeOrder.getUserid());
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        order.setGeneratedate(createTime);


        MakeOrderResp ret = new MakeOrderResp();
        ret.setCreateTime(createTime);
        // TODO: 2018/9/8
        int deliverPrice = DeliverPriceMapping.getPrice("111");
        ret.setDeliverPrice("" + deliverPrice);
        ret.setOrderId(order.getOrderid());
        // TODO: 2018/9/8
        ret.setTotalPrice(price + deliverPrice + "");
        ret.setTotalProductPrice(price + "");
        String prepayId = WXPay.makeWXPay(order.getOrderid(),ret.getTotalPrice(), order.getWaitpayid(), order.getUserid(), product);
        if ("".equals(prepayId)) {
            return new BaseResponse().setMess("获取支付信息错误").setCode(500);
        }
        order.setPayorderid(prepayId);
        orderDao.save(order);

        WXPayWeb wxPayWeb = new WXPayWeb(prepayId);
        String sign = Utils.signweb(wxPayWeb);

        ret.setNonceStr(wxPayWeb.getNonceStr());
        ret.setPackage_(wxPayWeb.getPackage_());
        ret.setPaySign(sign);
        ret.setTimeStamp(wxPayWeb.getTimeStamp());
        ret.setPayOrderId(prepayId);

        return new BaseResponse().setData(ret);
    }

    @Override
    @Transactional
    public BaseResponse makeWaitOrder(ReqMakeWaitOrder makeOrder) throws Exception {

        String orderid = makeOrder.getOrderid();
        OrderModel orderModel = orderDao.getOrderById(orderid);

        if (orderModel == null) {
            return new BaseResponse().setMess("非法操作").setCode(403);
        }

        //支付待支付订单时，商户订单号不能和之前的重复，所以需要更新waitpayid
        String newwaitpayid = UUID.randomUUID().toString().replace("-", "");
        orderDao.updateWaitPayId(orderid, newwaitpayid);
        orderModel.setWaitpayid(newwaitpayid);

        String productid = orderModel.getProductid();
        ProductModel product = productDao.getProductById(productid);
        int price = orderModel.getTotalprice();
        String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        MakeOrderResp ret = new MakeOrderResp();
        ret.setCreateTime(createTime);
        int deliverPrice = DeliverPriceMapping.getPrice("111");
        ret.setDeliverPrice("" + deliverPrice);
        ret.setOrderId(orderModel.getOrderid());
        ret.setTotalPrice(price + deliverPrice + "");
        ret.setTotalProductPrice(price + "");
        String prepayId = WXPay.makeWXPay(orderModel.getOrderid(),orderModel.getTotalprice() + "", orderModel.getWaitpayid(), orderModel.getUserid(), product);
        if ("".equals(prepayId)) {
            return new BaseResponse().setMess("获取支付信息错误").setCode(500);
        }

        orderDao.updatePrePayid(orderModel.getOrderid(), prepayId);

        WXPayWeb wxPayWeb = new WXPayWeb(prepayId);
        String sign = Utils.signweb(wxPayWeb);
        ret.setNonceStr(wxPayWeb.getNonceStr());
        ret.setPackage_(wxPayWeb.getPackage_());
        ret.setPaySign(sign);
        ret.setTimeStamp(wxPayWeb.getTimeStamp());
        ret.setPayOrderId(prepayId);

        return new BaseResponse().setData(ret);
    }

    @Override
    public DeliverResp getDeliverInfoByOrderId(String orderId) {
        DeliverResp deliverResp = new DeliverResp();
        OrderModel order = orderDao.getOrderById(orderId);
        ProductModel product = productDao.getProductById(order.getProductid());
        DeliverModel deliver = deliverDao.getDeliverByOrderId(orderId);
        DeliverResp.Inner inner = new DeliverResp.Inner();
        if (deliver != null) {
            inner.setExpress_number(deliver.getDeliverno());
            inner.setCompany(ZhDeliverComMapping.getDeliverCom(deliver.getCompany()));
        }
        inner.setDescription(product.getDetailname());
        inner.setDue_date(order.getGeneratedate());
        inner.setId(order.getOrderid());
        inner.setImage(product.getMainpic());
        inner.setStatus(OrderStatusMapping.getStatus(order.getStatus()));
        inner.setStatusnum(Integer.valueOf(order.getStatus()));
        inner.setStyle(BuyRentMapping.getBuyRent(order.getBuy_rent()));
        inner.setOrder_number(orderId);
        inner.setProductid(product.getProductid());
        inner.setBuyOrRent(Integer.valueOf(order.getBuy_rent()));
        inner.setTitle(product.getShortname());
        inner.setPhone(addressDao.getPhoneByUserId(order.getUserid()));
        inner.setNum(order.getQuantity());

        if (deliver != null && Constant.ORDER_STATUS_DELIVERING.equalsIgnoreCase(order.getStatus())) {
            deliverResp.setLogistics_detail(TransferSearch.SearchById(deliver.getCompany(), deliver.getDeliverno()));
        }
        deliverResp.setOrders(inner);
        return deliverResp;
    }

    @Override
    public ReturnableResp getOrdersReturnableOrder(String userid) {
        ReturnableResp ret = new ReturnableResp();
        List<OrderModel> orders = orderDao.getOrderByStatus(userid, Constant.ORDER_STATUS_DELIVERED);
        for (OrderModel order : orders) {
            if (!Constant.RENT.equals(order.getBuy_rent())) continue;
            ReturnableResp.Inner item = new ReturnableResp.Inner();
            ProductModel product = productDao.getProductById(order.getProductid());
            item.setId(order.getOrderid());
            item.setImage_url(product.getMainpic());
            int quantity = Integer.valueOf(order.getQuantity()) - Integer.valueOf(order.getRetquantity());
            if (quantity == 0) continue;
            item.setNum(quantity);
            item.setDescription(product.getDetailname());
            ret.addItem(item);
        }
        return ret;
    }

    @Override
    public ReturnableResp.Inner getOneReturn(String userid, String orderid) {
        OrderModel order = orderDao.getOrderById(orderid);
        ProductModel product = productDao.getProductById(order.getProductid());
        ReturnableResp.Inner one = new ReturnableResp.Inner();
        one.setDescription(product.getDetailname());
        one.setImage_url(product.getMainpic());
        one.setId(orderid);
        int quantity = Integer.valueOf(order.getQuantity()) - Integer.valueOf(order.getRetquantity());
        one.setNum(quantity);
        return one;
    }

    @Override
    @Transactional
    public BaseResponse confirmPay(String orderid) {
        BaseResponse response = new BaseResponse();
        OrderModel orderById = orderDao.getOrderById(orderid);

        if (orderById == null) {
            return new BaseResponse().setCode(400).setMess("非法操作");
        }

        String waitpayid = orderById.getWaitpayid();//最终是用这个id支付的
        WXPayResResp wxPayResResp = WXPay.searchPayRes(waitpayid);

        if (wxPayResResp == null) {
            //http请求失败
            return response.setCode(500).setMess("查询失败");
        }
        if (WXConstant.PAY_SUCCESS.equals(wxPayResResp.getTrade_state())) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            orderDao.confirmPay(orderid, sdf.format(new Date()));
            response.setData(WXConstant.PAY_SUCCESS);
        }
        response.setData(wxPayResResp.getTrade_state());
        return response;
    }

    @Override
    public BaseResponse opsNotDeliver() {
        List<OrderModel> orderModels = orderDao.notDeliver();

        List<NotDeliver> notDelivers = new ArrayList<>();

        for (OrderModel orderModel : orderModels) {
            NotDeliver notDeliver = new NotDeliver();
            ProductModel product = productDao.getProductById(orderModel.getProductid());
            AddressModel address = addressDao.getAddressByUserId(orderModel.getUserid());
            if (product == null) {
                continue;
            }

            notDeliver.setBuyrent(BuyRentMapping.getBuyRent(orderModel.getBuy_rent()));
            notDeliver.setCm(orderModel.getCm());
            notDeliver.setKg(orderModel.getKg());
            notDeliver.setOrderid(orderModel.getOrderid());
            notDeliver.setPaydate(orderModel.getPaydate());
            notDeliver.setProductid(product.getProductid());
            notDeliver.setUserid(orderModel.getUserid());
            notDeliver.setProductname(product.getDetailname());
            notDeliver.setWxpayid(orderModel.getPayorderid());
            notDeliver.setUsername(address.getUsername());
            notDeliver.setAddress(address.getProvince()+address.getCity()+address.getDistrict()+address.getDetail());
            notDeliver.setPhone(address.getPhone());
            notDeliver.setCount(orderModel.getQuantity());
            notDelivers.add(notDeliver);
        }


        return new BaseResponse().setData(notDelivers);
    }

    @Override
    public BaseResponse opsOrderByDeliverNo(String deliverno) {

        DeliverModel deliver = deliverDao.getDeliverByDeliverNo(deliverno);
        if (deliver != null) {
            OrderModel orderModel = orderDao.getOrderById(deliver.getOrderid());

            return new BaseResponse().setData(orderModel);
        }
        return new BaseResponse();
    }

    @Override
    public BaseResponse getWaitConfirmOrder() {
        List<OrderModel> orderByStatus = orderDao.getOrderByStatus(Constant.ORDER_STATUS_DELIVERING);
        return new BaseResponse().setData(orderByStatus);
    }


    @Override
    @Transactional
    public BaseResponse confirmOrder(ReqConfirmOrder reqConfirmOrder) {
        String orderid = reqConfirmOrder.getOrderid();
        String userid = reqConfirmOrder.getUserid();
        OrderModel order = orderDao.getOrderById(orderid);
        if (!order.getUserid().equals(userid)) {
            return new BaseResponse().setCode(403).setMess("非法操作");
        }
        if (Constant.ORDER_STATUS_DELIVERING.equals(order.getStatus())) {
            String reachTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            orderDao.confirmOrder(orderid, reachTime);
        } else {
            return new BaseResponse().setCode(403).setMess("非法操作");
        }
        return new BaseResponse();
    }

    @Override
    @Transactional
    public BaseResponse deliverOrder(ReqDeliverOrder order) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        OrderModel orderById = orderDao.getOrderById(order.getOrderid());
        if (orderById == null) {
            return new BaseResponse().setMess("此单不存在");
        }
        if (!Constant.ORDER_STATUS_PAID.equals(orderById.getStatus())) {
            return new BaseResponse().setMess("此单未支付或已处理");
        }
        orderDao.deliverOrder(order.getOrderid(), order.getDeliverid(), sdf.format(date));
        DeliverModel deliver = new DeliverModel();
        deliver.setCompany(order.getDelivercom());
        deliver.setDeliverno(order.getDeliverid());
        deliver.setDetime(sdf.format(date));
        deliver.setOrderid(order.getOrderid());
        deliver.setDeliverid(order.getDeliverid());
        deliverDao.save(deliver);
        return new BaseResponse();
    }

    @Override
    public BaseResponse removeOrder(ReqRemoveOrder removeOrder) {
        OrderModel order = orderDao.getOrderById(removeOrder.getOrderid());
        if (order.getUserid().equals(removeOrder.getUserid())) {
            orderDao.deleteById(removeOrder.getOrderid());
            return new BaseResponse();
        }
        return new BaseResponse().setCode(403).setMess("非法操作");
    }


    private double getDeliverPrice() {
        return 20.0;
    }
}
