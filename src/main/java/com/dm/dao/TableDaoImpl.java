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

    public DMTable getDMTable(Integer id) {


        String hq = "from DMTable d where d.id=?";

        Query query = sessionFactory.getCurrentSession().createQuery(hq);
        query.setInteger(0,id);

        return (DMTable) query.uniqueResult();
    }

    public List<DMTable> getAllDMTable() {

        String hql = "from DMTable";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        return query.list();
    }

    public void addDMTable(DMTable dmTable) {
        sessionFactory.getCurrentSession().save(dmTable);
//        sessionFactory.getCurrentSession().flush();
    }

    public boolean delDMTable(Integer id) {

        String hql = "delete DMTable t where t.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, id);

        return (query.executeUpdate() > 0);
    }

    public boolean updateDMTable(DMTable dmTable) {
        String hql = "update DMTable t set t.PoClassName = ?,t.PoClassName=? where t.id = ?";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setString(0, dmTable.getPoClassName());
        query.setString(1, dmTable.getMyTableName());
        query.setInteger(2, dmTable.getId());

        return (query.executeUpdate() > 0);
    }
}
