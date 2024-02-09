<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Menu</title>
</head>
<body>
<h1><%= "Menu" %></h1>
<br/>
<a href="${pageContext.request.contextPath}/patientslist">All patients</a>
<a href="${pageContext.request.contextPath}/signform">Sign in</a>
</body>
</html>