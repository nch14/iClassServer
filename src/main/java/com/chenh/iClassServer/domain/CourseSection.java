package com.chenh.iClassServer.domain;

import com.chenh.iClassServer.util.CourseTimeAndClassroom;
import com.chenh.iClassServer.util.CourseUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;

/**
 * Created by ChenhaoNee on 2016/11/28.
 */
@Entity
public class CourseSection {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 学号
     */
    private String userId;

    /**
     * 学期
     */
    private String term;
    /**
     * 课程编号
     */
    private Long courseId;

    /**
     * 原编号
     */
    private Long courseOldId;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 教师
     */
    private String courseTeacher;

    /**
     * 备注
     */
    private String note;

    /**
     * 选课类型
     */
    private String courseType;

    /**
     * 校区
     */
    private String campus;

    /**
     * 从那一节开始
     */
    private int startSection;

    /**
     * 持续几节课
     */
    private int lastSection;

    /**
     * 周几
     */
    private String courseDate;
    /**
     * 教室
     */
    private String courseClassroom;
    /**
     * 周数
     */
    private int week;

    public static ArrayList<CourseSection> getCourseFromJW(String userId,String[] data) {
        ArrayList<CourseSection> courseSections = new ArrayList<>();
        String timeAndClassroom = data[5];
        ArrayList<CourseTimeAndClassroom> courseTimeAndClassrooms = CourseUtil.parseDateAndClassroom(timeAndClassroom);
        for (CourseTimeAndClassroom courseTimeAndClassroom : courseTimeAndClassrooms) {
            for (int i = 0; i < courseTimeAndClassroom.weeks.length; i++) {
                CourseSection courseSection = new CourseSection();
                courseSection.courseId = Long.valueOf(data[0]);
                courseSection.courseOldId = Long.valueOf(data[1]);
                courseSection.courseName = data[2];
                courseSection.campus = data[3];
                courseSection.courseTeacher = data[4];
                courseSection.courseType = data[7];
                courseSection.courseClassroom=courseTimeAndClassroom.courseClassroom;
                courseSection.courseDate=courseTimeAndClassroom.courseDate;
                courseSection.startSection=CourseUtil.parseStartSection(courseTimeAndClassroom.courseTime);
                courseSection.lastSection=CourseUtil.parseLastSection(courseTimeAndClassroom.courseTime);
                courseSection.week=courseTimeAndClassroom.weeks[i];
                courseSection.userId=userId;
                courseSection.note=data[11]!=null?data[11]:"无";
                courseSection.term="暂无";
                courseSections.add(courseSection);
            }
        }
        return courseSections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseOldId() {
        return courseOldId;
    }

    public void setCourseOldId(Long courseOldId) {
        this.courseOldId = courseOldId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getStartSection() {
        return startSection;
    }

    public void setStartSection(int startSection) {
        this.startSection = startSection;
    }

    public int getLastSection() {
        return lastSection;
    }

    public void setLastSection(int lastSection) {
        this.lastSection = lastSection;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        this.courseDate = courseDate;
    }

    public String getCourseClassroom() {
        return courseClassroom;
    }

    public void setCourseClassroom(String courseClassroom) {
        this.courseClassroom = courseClassroom;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
