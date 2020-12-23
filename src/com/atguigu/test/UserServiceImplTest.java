package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        User user = new User(001,"yaozefan1","wsad0524","940163517@qq.com");
        userService.registUser(user);
    }

    @Test
    public void login() {
        User user = new User(001,"yaozefan1","wsad0524","940163517@qq.com");
        if(userService.login(user)==null){
            System.out.println("登录失败");
        }
        else System.out.println("登录成功");
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("yaozefan")){
            System.out.println("用户名已存在");
        }
        else System.out.println("用户名不存在");
    }
}