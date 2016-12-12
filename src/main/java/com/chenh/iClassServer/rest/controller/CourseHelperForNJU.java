package com.chenh.iClassServer.rest.controller;

import com.chenh.iClassServer.dao.CourseSectionDao;
import com.chenh.iClassServer.dao.URLManageDao;
import com.chenh.iClassServer.dao.UserDao;
import com.chenh.iClassServer.domain.CourseSection;
import com.chenh.iClassServer.domain.User;
import com.chenh.iClassServer.exception.ErrorPasswordException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by chenh on 2016/9/8.
 */
@Service
public class CourseHelperForNJU{

    @Autowired
    URLManageDao urlManageDao;
    @Autowired
    UserDao userDao;
    @Autowired
    CourseSectionDao courseSectionDao;

    Document doc = null;


    public Map<String,String> login(String id) throws IOException, ErrorPasswordException {
        HashMap<String, String> data = new HashMap<>();
        User user=userDao.findById(id);
        data.put("userName", id);
        data.put("password", user.getPassword());

        Connection.Response index = Jsoup.connect("http://jw.nju.edu.cn:8080/jiaowu/login.do"/*urlManageDao.findByName("jw_login").getValue()*/).data(data).timeout(1000).execute();
        Document doc=index.parse();
        Elements lb=doc.select("label");
        if (lb.size()!=0){
            String text =lb.get(0).text();
            if (text.equals("用户名或密码错误！"))
                throw new ErrorPasswordException("用户名或密码错误！");
        }
        return index.cookies();
    }

    private String[] getString(Elements tds) {
        int length = tds.size();
        String[] value = new String[length];
        for (int i = 0; i < length; i++) {
            value[i] = tds.get(i).text();
        }
        try {
            Elements e = tds.get(9).select("input");
            value[9] = e.get(0).attr("value");
        } catch (Exception e) {
            System.out.println("报错");
        }
        return value;
    }

    public boolean ImportFromUserCourseList(String userId,Map<String,String> cookies) throws IOException {
        String url;
        HashMap<String, String> params = new HashMap<>();

        url = "http://jw.nju.edu.cn:8080/jiaowu/student/teachinginfo/courseList.do"/*urlManageDao.findByName("jw_courseList").getValue()*/;
        params.put("method", "currentTermCourse");

        doc = Jsoup.connect(url).data(params).cookies(cookies).get();
        Elements trs = doc.select("table").select("tbody").select("table.TABLE_BODY").select("tr");
        for (int i = 1; i < trs.size(); i++) {
            Elements tds = trs.get(i).select("td");
            ArrayList<CourseSection> courses = CourseSection.getCourseFromJW(userId,getString(tds));
            courseSectionDao.save(courses);
        }
        return true;
    }

/*    public ArrayList<CourseSection> getCourseList(int type) {
        String url = urlManageDao.findByName("jw_login").getValue();
        HashMap<String, String> params = new HashMap<>();
        switch (type) {
            case CourseType.COMMON_COURSE:
                params.put("method", "discussRenewCourseList");
                params.put("campus", "仙林校区");
                break;
            default:
                url = null;
                break;
        }

        try {
            doc = Jsoup.connect(url).data(params).cookies(cookies).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements trs = doc.select("#tbCourseList").select("tr");
        ArrayList<CourseSection> courseList = new ArrayList<>();
        for (int i = 1; i < trs.size(); i++) {
            Elements tds = trs.get(i).select("td");

            //检查该课是否可选
            if (!tds.get(6).text().equals(tds.get(7).text())) {
                CourseSection course = new CourseSection(getString(tds), CourseSection.SELECT_COMMON_COURSE_SOURCE);
                courseList.add(course);
            }
        }
        return courseList;
    }


    public void autoChooseCourse(int type) {
        ArrayList<CourseSection> courseList = getCourseList(type);
        for (CourseSection course : courseList) {
            helpChoose(type, course.courseChooseValue);
        }
    }

    private String[] getString(Elements tds) {
        int length = tds.size();
        String[] value = new String[length];
        for (int i = 0; i < length; i++) {
            value[i] = tds.get(i).text();
        }
        try {
            Elements e = tds.get(9).select("input");
            value[9] = e.get(0).attr("value");
        } catch (Exception e) {
            System.out.println("报错");
        }
        return value;
    }

    private boolean helpChoose(int type, String classId) {
        String url = "http://jw.nju.edu.cn:8080/jiaowu/student/elective/courseList.do";
        HashMap<String, String> params = new HashMap<>();
        switch (type) {
            case CourseType.COMMON_COURSE:
                params.put("method", "submitDiscussRenew");
                params.put("campus", "仙林校区");
                params.put("classId", classId);
                break;
            default:
                url = null;
                break;
        }
        try {
            doc = Jsoup.connect(url).data(params).cookies(cookies).get();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("已经为您选中" + classId);
        return true;
    }*/



    /*public boolean quitClass(String classId) {
        String url;
        HashMap<String, String> params = new HashMap<>();

        url = "http://jw.nju.edu.cn:8080/jiaowu/student/elective/courseList.do";
        params.put("method", "deleteElectiveResult");
        params.put("classId", classId);

        try {
            doc = Jsoup.connect(url).data(params).cookies(cookies).get();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("已经为您选中" + classId);
        return true;
    }*/




    /*public static void main(String[] args){
        CourseHelperForNJU courseHelperForNJU=new CourseHelperForNJU();
        courseHelperForNJU.login("141250096","nch2012");
        //courseHelperForNJU.autoChooseCourse(CourseType.COMMON_COURSE);

*//*        ArrayList<CourseSection> courseArrayList = courseHelperForNJU.getCourseList(CourseType.COMMON_COURSE);
        System.out.println("The End");*//*
        courseHelperForNJU.getMyCourse();
    }*/
}
