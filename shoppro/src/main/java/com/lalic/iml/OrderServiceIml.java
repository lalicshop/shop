package com.lalic.iml;

import com.lalic.dao.CartDao;
import com.lalic.dao.DeliverDao;
import com.lalic.dao.OrderDao;
import com.lalic.dao.OrderDetailDao;
import com.lalic.dao.ProductDao;
import com.lalic.entity.DeliverModel;
import com.lalic.entity.Order;
import com.lalic.entity.OrderDetailModel;
import com.lalic.entity.OrderModel;
import com.lalic.entity.ProductModel;
import com.lalic.model.BaseResponse;
import com.lalic.model.body.AllOrderResp;
import com.lalic.model.body.DeliverResp;
import com.lalic.model.body.ReqConfirmOrder;
import com.lalic.model.body.ReqMakeOrder;
import com.lalic.model.body.ReturnableResp;
import com.lalic.service.OrderService;
import com.lalic.util.BuyRentMapping;
import com.lalic.util.Constant;
import com.lalic.util.DeliverComMapping;
import com.lalic.util.OrderStatusMapping;
import com.lalic.util.TransferSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Override
    public OrderModel getOrderById(String orderid) {
        return orderDao.getOrderById(orderid);
    }

    @Override
    public List<Order> getOrderDetailByUserid(String userid) {
        List<OrderModel> orderByUserid = orderDao.getOrderByUserid(userid);
        List<Order> orders = new ArrayList<>();
        orderByUserid.forEach(order -> {
            List<OrderDetailModel> orderDetailByOrderid = orderDetailDao.getOrderDetailByOrderid(order.getOrderid());
            orders.add(new Order(order, orderDetailByOrderid));
        });
        return orders;
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
            item.setDate(order.getDate());
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
    public void makeOrder(ReqMakeOrder makeOrder) {
        double totalPrize = 0;
        List<ReqMakeOrder.OrderDetail> details = makeOrder.getDetail();
        for (ReqMakeOrder.OrderDetail detail : details) {
            String productid = detail.getProductid();
            String count = detail.getCount();
            ProductModel product = productDao.getProductById(productid);
            double prize = 0.0;
            if (Constant.BUY.equalsIgnoreCase(makeOrder.getBuyOrRent())) {
                prize = Double.valueOf(product.getBuyprice());
            } else if (Constant.RENT.equalsIgnoreCase(makeOrder.getBuyOrRent())) {
                prize = Double.valueOf(product.getRentprice());
            }
            prize = prize * Integer.valueOf(count);
            totalPrize += prize;
        }
        OrderModel order = new OrderModel();
        order.setAddressid(makeOrder.getAddressid());
        order.setBuy_rent(makeOrder.getBuyOrRent());
        order.setProductid(makeOrder.getDetail().get(0).getProductid());
        order.setQuantity(makeOrder.getDetail().get(0).getCount());
        order.setTotalprice(totalPrize);
        order.setStatus(Constant.ORDER_STATUS_WAITFORPAY);
        order.setRetquantity("0");
        order.setUserid(makeOrder.getUserid());
        order.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        order.setComment(makeOrder.getComment());
        orderDao.save(order);
        cartDao.deleteById(makeOrder.getCartId());
    }

    @Override
    public DeliverResp getDeliverInfoByOrderId(String orderId) {
        DeliverResp deliverResp = new DeliverResp();
        OrderModel order = orderDao.getOrderById(orderId);
        ProductModel product = productDao.getProductById(order.getProductid());
        DeliverModel deliver = deliverDao.getDeliverByOrderId(orderId);
        DeliverResp.Inner inner = new DeliverResp.Inner();
        inner.setCompany(DeliverComMapping.getDeliverCom(deliver.getCompany()));
        inner.setDescription(product.getDetailname());
        inner.setDue_date(order.getDate());
        inner.setExpress_number(deliver.getDeliverno());
        inner.setId(order.getOrderid());
        inner.setImage(product.getMainpic());
        inner.setStatus(OrderStatusMapping.getStatus(order.getStatus()));
        inner.setStyle(BuyRentMapping.getBuyRent(order.getBuy_rent()));

        if (Constant.ORDER_STATUS_DELIVERING.equalsIgnoreCase(order.getStatus()) || Constant.ORDER_STATUS_RETURNING.equalsIgnoreCase(order.getStatus())) {
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
            item.setNum(quantity + "");
            item.setDescription(product.getDetailname());
            ret.addItem(item);
        }
        return ret;
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
}
