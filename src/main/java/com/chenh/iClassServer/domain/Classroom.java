package com.chenh.iClassServer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by chenh on 2016/7/27.
 */
@Entity
public class Classroom {

    @Id
    @GeneratedValue
    private Long id;

    private String nameToShow;

    public String getNameToShow() {
        return nameToShow;
    }

    public void setNameToShow(String nameToShow) {
        this.nameToShow = nameToShow;
    }
}
