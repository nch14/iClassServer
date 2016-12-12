package com.chenh.iClassServer.rest.controller;

import com.chenh.iClassServer.dao.CourseSectionDao;
import com.chenh.iClassServer.domain.CourseSection;
import com.chenh.iClassServer.domain.Message;
import com.chenh.iClassServer.domain.User;
import com.chenh.iClassServer.exception.ErrorPasswordException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ChenhaoNee on 2016/11/28.
 */
@RestController
@RequestMapping(value = "/v1/courses")
public class CourseSectionController {

    @Autowired
    private CourseSectionDao courseSectionDao;

    @ApiOperation(value = "获取课程信息", notes = "")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "学生id",
                    required = true, dataType = "String"),
            @ApiImplicitParam(name = "week", value = "要查看的周次",
                    required = true, dataType = "Integer")
    })
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<CourseSection> getCourseList(@RequestParam(value = "id") String id,
                         @RequestParam(value = "week") int weekIndex) {
        List<CourseSection> c= courseSectionDao.findByUserIdAndWeek(id, weekIndex);
        return c;
    }

    @Autowired
    CourseHelperForNJU courseHelperForNJU;

    @ApiOperation(value = "自动导入课程", notes = "")
    @ApiImplicitParam(name = "id", value = "id",
            required = true, dataType = "String")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public Message autoImportCourse(@RequestParam(value = "id") String id) {
        Map<String, String> cookies = null;
        for (int i = 0; i < 3; i++) {
            try {
                cookies = courseHelperForNJU.login(id);
                break;
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            } catch (ErrorPasswordException e) {
                return new Message("密码错误，请重新登陆", false);
            }
        }
        if (cookies == null)
            return new Message("学校服务器可能正在维护中……请稍后重试", false);
        Boolean finish = false;
        for (int i = 0; i < 3; i++) {
            try {
                finish = courseHelperForNJU.ImportFromUserCourseList(id,cookies);
                break;
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
        if (!finish) {
            return new Message("学校服务器可能正在维护中……请稍后重试", false);
        } else
            return new Message("课程导入成功", true);
    }

}
