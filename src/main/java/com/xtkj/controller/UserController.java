package com.xtkj.controller;

import com.github.pagehelper.PageInfo;
import com.xtkj.pojo.User;
import com.xtkj.service.IUserService;
import com.xtkj.tools.GsonResult;
import com.xtkj.tools.TestTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/getUsers")
    public @ResponseBody GsonResult<PageInfo<User>> main(@RequestParam(value = "pageNumber") Integer offset, @RequestParam(value = "pageSize")  Integer limit){
        int newOffset = (offset-1)*limit;
        GsonResult<PageInfo<User>> users = userService.getUsers(newOffset, limit);
        return users;
    }

    @RequestMapping("/delUser")
    @ResponseBody
    public GsonResult delUser(HttpServletRequest request){
        String userId = request.getParameter("userId");
        return userService.deleteUser(Integer.parseInt(userId));
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public GsonResult addUser(HttpServletRequest request){
        String userName = request.getParameter("userName");
        String loginId = request.getParameter("loginId");
        String loginPwd = request.getParameter("loginPwd");
        User user = (User) TestTool.INSTALL.getBeanFactory("user");
        user.setUserName(userName);
        user.setLoginId(loginId);
        user.setLoginPwd(loginPwd);
        return userService.addUser(user);
    }

    @RequestMapping("/updUser")
    @ResponseBody
    public GsonResult updUser(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String loginId = request.getParameter("loginId");
        String loginPwd = request.getParameter("loginPwd");
        User user = (User) TestTool.INSTALL.getBeanFactory("user");
        user.setUserId(Integer.parseInt(userId));
        user.setUserName(userName);
        user.setLoginId(loginId);
        user.setLoginPwd(loginPwd);
        return userService.updateUser(user);
    }



}
