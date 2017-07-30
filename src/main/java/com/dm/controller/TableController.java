package com.dm.controller;

import com.dm.entity.DMTable;
import com.dm.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: 景贝贝
 * Date: 2017/7/29
 * Time: 18:25
 */
@Controller
@RequestMapping("/table")
public class TableController {
    //注入service
    @Autowired
    private TableService tableService;

//    @Autowired
//    private CustomerService customerService;

    @RequestMapping("/getAllTable")
    public String getAllUser(HttpServletRequest request) {

        request.setAttribute("tableList", tableService.getAllDMTable());

        return "/index";
    }

    @RequestMapping("/getTable")
    public String getUser(String id, HttpServletRequest request) {

        request.setAttribute("table", tableService.getDMTable(id));

        return "/editUser";
    }

    @RequestMapping("/toAddTable")
    public String toAddTable() {
        return "/addTable";
    }

    @RequestMapping("/addTable")
    public String addTable(DMTable dmTable, HttpServletRequest request) {
        try {
            tableService.addDMTable(dmTable);
            return "redirect:/table/getAllTable";
        } catch (Exception e) {
            return "/error";
        }
    }

    @RequestMapping("/delTable")
    public void delTable(String id, HttpServletResponse response) {

        String result = "{\"result\":\"error\"}";

        if (tableService.delDMTable(id)) {
            result = "{\"result\":\"success\"}";
        }

        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/updateTable")
    public String updateUser(DMTable dmTable, HttpServletRequest request) {

        if (tableService.updateDMTable(dmTable)) {
            dmTable = tableService.getDMTable(dmTable.getId());
            request.setAttribute("dmTable", dmTable);
            return "redirect:/table/getAllTable";
        } else {
            return "/error";
        }
    }
}
