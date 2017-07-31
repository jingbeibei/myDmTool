package com.dm.service;

import com.dm.entity.DMTable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 景贝贝
 * Date: 2017/7/29
 * Time: 19:24
 */
public interface TableService {
    public DMTable getDMTable(Integer id);

    public List<DMTable> getAllDMTable();

    public void addDMTable(DMTable dmTable);

    public boolean delDMTable(Integer id);

    public boolean updateDMTable(DMTable dmTable);
}
