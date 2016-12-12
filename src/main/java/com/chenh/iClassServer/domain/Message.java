package com.chenh.iClassServer.domain;

/**
 * Created by ChenhaoNee on 2016/11/28.
 */
public class Message {
    public String description;
    public Boolean success;
    public Object object;

    public Message(String description,Boolean success){
        this.description=description;
        this.success=success;
    }

    public Message(String description,Boolean success,Object object){
        this.description=description;
        this.success=success;
        this.object=object;
    }
}
