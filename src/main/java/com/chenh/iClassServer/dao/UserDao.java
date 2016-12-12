package com.chenh.iClassServer.dao;

/**
 * Created by ChenhaoNee on 2016/11/27.
 */

import javax.persistence.Table;

import com.chenh.iClassServer.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "User")
/*@Qualifier("UserDao")*/
public interface UserDao extends CrudRepository<User, Long> {

    public User findById(String id);


}
