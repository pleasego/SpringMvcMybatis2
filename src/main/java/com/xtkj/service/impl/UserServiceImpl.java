package com.xtkj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtkj.dao.UserMapper;
import com.xtkj.pojo.User;
import com.xtkj.service.IUserService;
import com.xtkj.tools.GsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    private GsonResult gsonResult = new GsonResult();

    @Override
    public GsonResult deleteUser(Integer id) {
        //主键id有外键
        int i = userMapper.deleteByPrimaryKey(id);
        if(i>0){
            gsonResult.setMsg("删除成功");
            gsonResult.setStateCode(200);
        }else{
            gsonResult.setStateCode(500);
            gsonResult.setMsg("删除失败");
        }
        return gsonResult;
    }

    @Override
    public GsonResult updateUser(User user) {
        int i = userMapper.updateByPrimaryKeySelective(user);
        if(i>0){
            gsonResult.setMsg("修改成功");
            gsonResult.setStateCode(200);
        }else{
            gsonResult.setStateCode(500);
            gsonResult.setMsg("修改失败");
        }
        return gsonResult;
    }

    @Override
    public GsonResult addUser(User user) {
        int i = userMapper.insertSelective(user);
        if(i>0){
            gsonResult.setMsg("添加成功");
            gsonResult.setStateCode(200);
        }else{
            gsonResult.setStateCode(500);
            gsonResult.setMsg("添加失败");
        }
        return gsonResult;
    }

    @Override
    public GsonResult<PageInfo<User>> getUsers(Integer offset, Integer limit) {
        PageHelper.offsetPage(offset,limit);
        List<User> users = userMapper.getUsers();
        PageInfo pageInfo = new PageInfo<>(users);
        GsonResult<PageInfo<User>> gsonResult = new GsonResult<>();
        gsonResult.setStateCode(200);
        gsonResult.setMsg("ok");
        gsonResult.setObj(pageInfo);
        return gsonResult;
    }
}
