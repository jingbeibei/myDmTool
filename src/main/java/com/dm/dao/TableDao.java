package com.dm.dao;

import com.dm.entity.DMTable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 景贝贝
 * Date: 2017/7/29
 * Time: 18:58
 */
public interface TableDao {
    public DMTable getDMTable(String id);

    public List<DMTable> getAllDMTable();

    public void addDMTable(DMTable dmTable);

    public boolean delDMTable(String id);

    public boolean updateDMTable(DMTable dmTable);
}
