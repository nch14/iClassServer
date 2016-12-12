package com.chenh.iClassServer.dao;

import com.chenh.iClassServer.domain.URLManage;
import com.chenh.iClassServer.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * Created by ChenhaoNee on 2016/11/28.
 */
@Repository
@Table(name = "URLManage")
/*@Qualifier("URLManageDao")*/
public interface URLManageDao extends CrudRepository<URLManage, Long> {
    public URLManage findByName(String name);
}
