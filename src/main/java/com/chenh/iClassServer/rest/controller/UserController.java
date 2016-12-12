package com.chenh.iClassServer.rest.controller;

import com.chenh.iClassServer.dao.UserDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.chenh.iClassServer.domain.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ChenhaoNee on 2016/11/25.
 */
@RestController
@RequestMapping(value = "/v1/users")     // 通过这里配置使下面的映射都在/users下
public class UserController {

    @Autowired
    private UserDao userDao;

    @ApiOperation(value = "获取用户信息", notes = "根据url的id来获取用户信息")
    @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    @RequestMapping(method = RequestMethod.GET)
    public User getUser(@RequestParam(value = "id") String id,
                        HttpServletRequest request, HttpServletResponse response) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中

        User user = userDao.findById(id);
        return user;
    }

    @ApiOperation(value = "创建一个新用户", notes = "将客户端创建的新用户保存在服务器")
    @ApiImplicitParam(name = "user", value = "User的值", required = true, dataType = "User")
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void addUser(@RequestBody User user,
                        HttpServletRequest request, HttpServletResponse response) {
        userDao.save(user);
    }


}
