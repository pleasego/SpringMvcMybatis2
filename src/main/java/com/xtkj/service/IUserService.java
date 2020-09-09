package com.xtkj.service;

import com.github.pagehelper.PageInfo;
import com.xtkj.pojo.User;
import com.xtkj.tools.GsonResult;
import org.springframework.stereotype.Service;


public interface IUserService {

    GsonResult deleteUser(Integer id);

    GsonResult updateUser(User user);

    GsonResult addUser(User user);

    GsonResult<PageInfo<User>> getUsers(Integer offset,Integer limit);
}
