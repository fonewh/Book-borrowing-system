package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends BaseServlet {
    private UserServiceImpl userServiceImpl =new UserServiceImpl();
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        //调用WebUtils创建user并注入属性，再将user赋给userSerImpl的login方法
        User loginuser = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        if (userServiceImpl.login(loginuser)==null){
            //把错误信息和回显的表单项信息，保存到Request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",loginuser.getUsername());
            //登录失败，跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
        else{
            //登录成功，跳到登录成功页面
            req.getSession().setAttribute("user",loginuser);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);

        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        1.获取请求参数
        String token = (String) req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");

        //调用WebUtils创建user并注入属性
        User user=WebUtils.copyParamToBean(req.getParameterMap(),new User());
        //2.检测验证码是否正确
        if(token.equalsIgnoreCase(code)){
            //3.检查用户名是否可用
            if (userServiceImpl.existsUsername(user.getUsername())){
                //用户名被占用处理
                //回显数据，反馈
                req.setAttribute("msg","用户名被占用");
                req.setAttribute("email",user.getEmail());
                //跳回注册页面
                System.out.println("用户名被占用");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }
            else{
                //登录成功，跳转登录成功页面
                userServiceImpl.registUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }

        }
        else {
            //验证码错误
            //反馈用户，回显数据
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("email",user.getEmail());

            //跳回注册页面
            System.out.println("验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean existsUsername = userServiceImpl.existsUsername(username);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
