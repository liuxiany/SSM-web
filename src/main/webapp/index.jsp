<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <body>
        <h2>Hello World!</h2>
        <h4>添加用户</h4>
        <form action="${pageContext.request.contextPath}/demo/addUser.htm" method="post">
            姓名：<input name="name" type="text"/>
            <input type="submit" value="提交"/>
        </form>

    </body>
</html>
