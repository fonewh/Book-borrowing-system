package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",2,new BigDecimal(100),new BigDecimal(100),"12345"));
        orderItemDao.saveOrderItem(new OrderItem(null,"oracle从入门到精通",2,new BigDecimal(200),new BigDecimal(400),"12345"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript从入门到精通",3,new BigDecimal(300),new BigDecimal(900),"12345"));

    }
}