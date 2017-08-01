package com.dm.controller;

import com.dm.entity.DMTable;
import com.dm.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    String re1 = "(@)";    // Any Single Character 1
    String re2 = "(Table)";    // Word 1
    String re3 = "(\\()";    // Any Single Character 2
    String re10 = "(\\s*)";
    String re4 = "(name)";    // Word 2
    String re5 = "(=)";    // Any Single Character 3
    String re6 = "(\")";    // Any Single Character 4
    String re7 = "((?:[a-z][a-z0-9_]*))";    // Variable Name 1
    String re8 = "(\")";    // Any Single Character 5

    Pattern p = Pattern.compile(re1 + re10 + re2 + re10 + re3 + re10 + re4 + re10 + re5 + re10 + re6 + re10 + re7 + re10 + re8, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
    Matcher m;

//    @Autowired
//    private CustomerService customerService;

    @RequestMapping("/getAllTable")
    public String getAllTable(HttpServletRequest request) {

        request.setAttribute("tableList", tableService.getAllDMTable());

        return "/index";
    }

    @RequestMapping("/getTable")
    public String getTable(Integer id, HttpServletRequest request) {

        request.setAttribute("table", tableService.getDMTable(id));

        return "/editTable";
    }

    @RequestMapping("/toAddTable")
    public String toAddTable() {
        return "/addTable";
    }

    @RequestMapping("/addTable")
    public String addTable(DMTable dmTable, HttpServletRequest request) {
        String mypath = request.getParameter("path");
        String projectName=request.getParameter("projectName");
        List<String> filenames = new ArrayList<String>();
        List<String> tableames = new ArrayList<String>();

        getFile(mypath, filenames, tableames);
        if (filenames != null && tableames != null) {

            int number = filenames.size();
            int number2 = tableames.size();
            try {
                for (int i = 0; i < number; i++) {
                    DMTable dmTable1 = new DMTable();
                    dmTable1.setPoClassName(filenames.get(i));
                    dmTable1.setMyTableName(tableames.get(i));
                    dmTable1.setProjectName(projectName);
                    tableService.addDMTable(dmTable1);
                }
                return "redirect:/table/getAllTable";
            } catch (Exception e) {
                return "/error";
            }
        } else {
            return "/error";
        }
    }

    @RequestMapping("/delTable")
    public void delTable(Integer id, HttpServletResponse response) {

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
    public String updateTable(DMTable dmTable, HttpServletRequest request) {

        if (tableService.updateDMTable(dmTable)) {
            dmTable = tableService.getDMTable(dmTable.getId());
            request.setAttribute("dmTable", dmTable);
            return "redirect:/table/getAllTable";
        } else {
            return "/error";
        }
    }

    private void getFile(String path, List<String> filenames, List<String> tablenames) {
        // 获得指定文件对象
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();
        FileReader reader = null;

        if (array != null) {

            for (int i = 0; i < array.length; i++) {
                if (array[i].isFile() && array[i].getName().endsWith(".java"))//如果是文件
                {
                    try {
                        reader = new FileReader(array[i].getPath());
                        BufferedReader br = new BufferedReader(reader);
                        String str = null;
                        while ((str = br.readLine()) != null) {
                            str = getTableName(str);
                            if (!"".equals(str))
                                break;
                        }
                        if (null != str && !"".equals(str)) {
                            tablenames.add(str);
                            filenames.add(array[i].getName().substring(0, array[i].getName().lastIndexOf(".")));
                        }
                        br.close();
                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (array[i].isDirectory())//如果是文件夹
                {
//                System.out.println(array[i].getName());
                    getFile(array[i].getPath(), filenames, tablenames);
                }
            }
        }


        //版本一
//        if (array != null) {
//
//            for (int i = 0; i < array.length; i++) {
//                if (array[i].isFile())//如果是文件
//                {
//                    try {
//                        reader = new FileReader(array[i].getPath());
//                        BufferedReader br = new BufferedReader(reader);
//                        String str = null;
//                        while ((str = br.readLine()) != null) {
//                            str = getTableName(str);
//                            if (!"".equals(str))
//                                break;
//                        }
//                        tablenames.add(str);
//                        br.close();
//                        reader.close();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    filenames.add(array[i].getName().substring(0, array[i].getName().lastIndexOf(".")));
//
//                } else if (array[i].isDirectory())//如果是文件夹
//                {
////                System.out.println(array[i].getName());
//                    getFile(array[i].getPath(), filenames, tablenames);
//                }
//            }
//        }
    }

    private String getTableName(String string) {//获取文件中的表名
        m = p.matcher(string);
        if (m.find()) {
            return m.group(13);
        } else {
            return "";
        }
    }


}
