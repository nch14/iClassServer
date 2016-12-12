package com.chenh.iClassServer.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ChenhaoNee on 2016/11/28.
 */
@Entity
public class OAuth {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private String oCode;

    private String validDate;
}


