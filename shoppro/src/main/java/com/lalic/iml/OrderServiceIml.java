package com.lalic.iml;

import com.lalic.dao.AddressDao;
import com.lalic.dao.CartDao;
import com.lalic.dao.DeliverDao;
import com.lalic.dao.OrderDao;
import com.lalic.dao.OrderDetailDao;
import com.lalic.dao.ProductDao;
import com.lalic.entity.CartModel;
import com.lalic.entity.DeliverModel;
import com.lalic.entity.OrderModel;
import com.lalic.entity.ProductModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.AllOrderResp;
import com.lalic.model.body.DeliverResp;
import com.lalic.model.body.MakeOrderResp;
import com.lalic.model.body.ReqConfirmOrder;
import com.lalic.model.body.ReqDeliverOrder;
import com.lalic.model.body.ReqMakeOrder;
import com.lalic.model.body.ReqRemoveOrder;
import com.lalic.model.body.ReturnableResp;
import com.lalic.service.OrderService;
import com.lalic.util.BuyRentMapping;
import com.lalic.util.Constant;
import com.lalic.util.DeliverComMapping;
import com.lalic.util.DeliverPriceMapping;
import com.lalic.util.OrderStatusMapping;
import com.lalic.util.TransferSearch;
import com.lalic.wx.WXConstant;
import com.lalic.wx.WXPay;
import com.lalic.wx.WXPayResResp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public BaseResponse makeOrder(ReqMakeOrder makeOrder){
        CartModel cartById = cartDao.getCartById(makeOrder.getCartId());

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
        int deliverPrice = DeliverPriceMapping.getPrice("10");
        ret.setDeliverPrice(deliverPrice + "");
        ret.setOrderId(order.getOrderid());
        // TODO: 2018/9/8
        ret.setTotalPrice(price + deliverPrice + "");
        ret.setTotalProductPrice(price + "");
        String prepayId = WXPay.makeWXPay(ret, order, product);
//        String prepayId = "abc";
        if ("".equals(prepayId)) {
            return new BaseResponse().setMess("获取支付信息错误").setCode(500);
        }
        order.setPayorderid(prepayId);
        orderDao.save(order);
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
            inner.setCompany(DeliverComMapping.getDeliverCom(deliver.getCompany()));
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

        if (Constant.ORDER_STATUS_DELIVERING.equalsIgnoreCase(order.getStatus())) {
            deliverResp.setLogistics_detail(TransferSearch.SearchById(order.getDeliverid()));
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

        WXPayResResp wxPayResResp = WXPay.searchPayRes(orderid);
        if (wxPayResResp == null) {
            //http请求失败
            return response.setCode(500).setMess("查询失败");
        }
        if (WXConstant.PAY_SUCCESS.equals(wxPayResResp.getTrade_state())) {
            orderDao.confirmPay(orderid);
            response.setData(WXConstant.PAY_SUCCESS);
        }
        return response;
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
        orderDao.confirmOrder(orderid);
        return new BaseResponse();
    }

    @Override
    @Transactional
    public BaseResponse deliverOrder(ReqDeliverOrder order) {
        orderDao.deliverOrder(order.getOrderid(), order.getDeliverid());
        DeliverModel deliver = new DeliverModel();
        deliver.setCompany(order.getDelivercom());
        deliver.setDeliverno(order.getDeliverid());
        deliver.setDetime(new Date().getTime());
        deliver.setOrderid(order.getOrderid());
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
