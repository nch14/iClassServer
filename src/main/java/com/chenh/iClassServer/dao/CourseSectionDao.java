package com.chenh.iClassServer.dao;

import com.chenh.iClassServer.domain.CourseSection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenhaoNee on 2016/11/28.
 */

@Repository
@Table(name = "CourseSection")
/*@Qualifier("CourseDao")*/
public interface CourseSectionDao extends JpaRepository<CourseSection, Long> {

    List<CourseSection> findByUserIdAndWeek(String userId,int week);

}
