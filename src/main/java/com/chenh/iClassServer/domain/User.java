package com.chenh.iClassServer.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by chenh on 2016/7/27.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")
    private String id;//学号

    private String name;//姓名

    private boolean isBoy;//性别

    private String birthday;//生日

    private String idCode;//身份证号

    private String institute;//学院

    private String department;//所在专业

    private String speciality;//专业方向

    private String enterYear;//入学年级

    private String belongYear;//所属年级

    private String system;//学制

    private String nickName;//昵称

    private String motto;//签名

    private String university;//所在学校

    private String password;//密码

    public boolean isBoy() {
        return isBoy;
    }

    public void setBoy(boolean boy) {
        isBoy = boy;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getEnterYear() {
        return enterYear;
    }

    public void setEnterYear(String enterYear) {
        this.enterYear = enterYear;
    }

    public String getBelongYear() {
        return belongYear;
    }

    public void setBelongYear(String belongYear) {
        this.belongYear = belongYear;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

}
