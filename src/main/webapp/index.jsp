<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
    <body>
        <h2>Hello World!</h2>
        <h4>添加用户</h4>
        <form action="${pageContext.request.contextPath}/demo/addUser.htm" method="post">
            姓名：<input name="name" type="text"/>
            <input type="submit" value="提交"/>
        </form>

        <h4>根据用户name，模糊查询用户信息</h4>
        <form action="${pageContext.request.contextPath}/demo/getUsersByName.htm" method="get">
            姓名：<input name="name" type="text">
            <input type="submit" value="查找"/>
        </form>
    </body>
</html>
