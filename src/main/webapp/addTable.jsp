<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
        function addTable() {
            var form = document.forms[0];
            form.action = "/myDmTool/table/addTable";
            form.method = "post";
            form.submit();
        }
    </script>
</head>
<body>
<h1>添加表</h1>
<form action="" name="userForm">
    <table>
        <tr>
            <td>工程名：</td>
            <td><input id="projectName" type="text" name="projectName" size="30"></td>
        </tr>
        <tr>
            <td>选择导入数据源：</td>
            <td><input id="path" type="text" name="path" size="30"></td>
            <td><input type=button value="选择" onclick="browseFolder('path')"></td>
        </tr>

    </table>


    <input type="button" value="添加" onclick="addTable()">
</form>


</body>

<script type="text/javascript">
    function browseFolder(path) {
        try {
            var Message = "\u8bf7\u9009\u62e9\u6587\u4ef6\u5939"; //选择框提示信息
            var Shell = new ActiveXObject("Shell.Application");
            var Folder = Shell.BrowseForFolder(0, Message, 64, 17); //起始目录为：我的电脑
            //var Folder = Shell.BrowseForFolder(0, Message, 0); //起始目录为：桌面
            if (Folder != null) {
                Folder = Folder.items(); // 返回 FolderItems 对象
                Folder = Folder.item(); // 返回 Folderitem 对象
                Folder = Folder.Path; // 返回路径
                if (Folder.charAt(Folder.length - 1) != "\\") {
                    Folder = Folder + "\\";
                }
                document.getElementById(path).value = Folder;
                return Folder;
            }
        }
        catch (e) {
            alert(e.message);
        }
    }


</script>

</html>  