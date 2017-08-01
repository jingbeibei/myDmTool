<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<script type="text/javascript" src="../js/jquery-1.10.2.js"></script>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Insert title here</title>  
</head>  
<body>  
    <h1>编辑表</h1>
    <form action="/myDmTool/table/updateTable" name="tableForm" method="post">
        <input type="hidden" name="id" value="${table.id }">
       PO类名：<input type="text" name="poClassName" value="${table.poClassName }">
        表名：<input type="text" name="myTableName" value="${table.myTableName }">
        <input type="submit" value="编辑" >  
    </form>  
</body>  
</html>  