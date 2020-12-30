<%--
  Created by IntelliJ IDEA.
  User: 30712
  Date: 2020-12-30
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    //写在类里面的，表示的是类的属性，是类级别的
    private int count;
%>
<%
    //是写在doGet方法中的
    count++;
    request.setCharacterEncoding("utf-8");
    String name = request.getParameter("name");
%>
<html>
<head>
    <meta charset = "utf-8">
    <title>JSP</title>

    <link rel ="stylesheet" href ="css/style.css">
</head>
<body>

<h1><%= name%> 的空间</h1>
<div>访问次数为：<%= count %></div>
<div class ="hobby">
    <h2><%= name%> 的爱好</h2>
    <ul>
        <li>追剧</li>
        <li>敲代码</li>
        <li>吃火锅</li>
    </ul>
</div>

<div>
    <h2><%= name%> 的理想</h2>
    <ol>
        <li><a href="https://www.tencent.com/zh-cn">去大厂</a></li>
        <li>拿高薪</li>
        <li>找到一个好工作</li>
    </ol>
</div>

<img src="image/profile.jpg" width = "200" height="200">
</body>
</html>
