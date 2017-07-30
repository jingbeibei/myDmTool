package com.dm.service;

import com.dm.dao.TableDao;
import com.dm.entity.DMTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 景贝贝
 * Date: 2017/7/29
 * Time: 19:24
 */
@Service
public class TableServiceImpl implements TableService {
    @Autowired
    private TableDao tableDao;
    public DMTable getDMTable(String id) {
        return tableDao.getDMTable(id);
    }

    public List<DMTable> getAllDMTable() {
        return tableDao.getAllDMTable();
    }

    public void addDMTable(DMTable dmTable) {

    }

    public boolean delDMTable(String id) {
        return tableDao.delDMTable(id);
    }

    public boolean updateDMTable(DMTable dmTable) {
        return tableDao.updateDMTable(dmTable);
    }
}
