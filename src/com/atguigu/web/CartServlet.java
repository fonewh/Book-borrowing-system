package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    BookServiceImpl bookService = new BookServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id值
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //通过id找到book对象
        Book book = bookService.queryBookById(id);
        //通过book对象的值 创建一个cartItem对象
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //获取cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //如果没有cart对象就新建一个
        if (cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        //将cartItem传入cart中
        cart.addItem(cartItem);

        //最后一个添加的商品名称
        req.getSession().setAttribute("lastname",cartItem.getName());

//        //请求重定向回购物车原来的页面
//        resp.sendRedirect(req.getHeader("Referer"));
        //将最后添加的商品和购物车总数发送给客户端
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("lastName",cartItem.getName());
        resultMap.put("totalCount",cart.getTotalCount());
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);

    }
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id值
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //获取cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.deleteItem(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            cart.clear();
        }
        req.getSession().setAttribute("lastname",null);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void  updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            cart.updateCount(id,count);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
