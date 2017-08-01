<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="../js/jquery-1.10.2.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
        function del(id){
            $.get("/myDmTool/table/delTable?id=" + id,function(data){
                if("success" == data.result){
                    alert("删除成功");
                    window.location.reload();
                }else{
                    alert("删除失败");
                }
            });
        }
    </script>
</head>
<body>
<h2><a href="/myDmTool/table/toAddTable">添加表</a>&nbsp;&nbsp;<a href="/myDmTool/table/getAllTable">查看表</a></h2>

<table border="1">
    <tbody>
    <tr>
        <td>编号</td>
        <th>PO类名</th>
        <th>表名</th>
        <td>工程名</td>
        <th>操作</th>
    </tr>
    <c:if test="${!empty tableList }">
        <c:forEach items="${tableList }" var="table">
            <tr>
                <td>${table.id }</td>
                <td>${table.poClassName }</td>
                <td>${table.myTableName }</td>
                <td>${table.projectName }</td>
                <td>
                    <a href="/myDmTool/table/getTable?id=${table.id }">编辑</a>
                    <a href="javascript:del('${table.id }')">删除</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
</body>
</html>