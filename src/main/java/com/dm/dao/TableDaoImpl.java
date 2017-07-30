package com.dm.dao;

import com.dm.entity.DMTable;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 景贝贝
 * Date: 2017/7/29
 * Time: 18:59
 */
@Repository
public class TableDaoImpl implements TableDao{
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public DMTable getDMTable(String id) {


        String hq = "from DMTable d where d.id=?";

        Query query = sessionFactory.getCurrentSession().createQuery(hq);
        query.setString(0, id);

        return (DMTable) query.uniqueResult();
    }

    public List<DMTable> getAllDMTable() {

        String hql = "from DMTable";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    public void addDMTable(DMTable dmTable) {
        sessionFactory.getCurrentSession().save(dmTable);
    }

    public boolean delDMTable(String id) {
        return false;
    }

    public boolean updateDMTable(DMTable dmTable) {
        return false;
    }
}
