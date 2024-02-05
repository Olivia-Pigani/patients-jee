<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="WEB-INF/shared/bootstrap.jsp"%>

<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
  session = request.getSession();
  String mode = request.getParameter("mode");
  if (mode == null){
    mode = "signin";
  }
%>

<html>
<head>
    <title>Authentification Page</title>
</head>
<body>

<div class="container-md">

  <h1><%=mode.equals("signin")? "Sign In" : "Sign Up" %></h1>

  <% if (mode.equals("signin")) { %>
  <form action="/authservlet" method="POST">
    <div class="mb-3">
      <label for="exampleInputEmail1" class="form-label">Email address</label>
      <input type="email" class="form-control" id="exampleInputEmail" name="email" aria-describedby="emailHelp" required>
    </div>
    <div class="mb-3">
      <label for="exampleInputPassword1" class="form-label">Password</label>
      <input type="password" class="form-control" id="exampleInputPassword" name="password" required>
    </div>

    <button type="submit" name="action" value="signin" class="btn btn-primary">Sign In</button>


  </form>

  <div>

    <a href="?mode=signup">Sign Up</a>
  </div>
<% } else { %>

  <form action="/authservlet" method="POST">
    <div class="mb-3">
      <label for="exampleInputPassword1" class="form-label">Name</label>
      <input type="text" class="form-control" id="name" name="name" required>
    </div>
    <div class="mb-3">
      <label for="exampleInputEmail1" class="form-label">Email address</label>
      <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp" required>
    </div>
    <div class="mb-3">
      <label for="exampleInputPassword1" class="form-label">Password</label>
      <input type="password" class="form-control" id="exampleInputPassword1" name="password" required>
    </div>


    <button type="submit" name="action" value="signup" class="btn btn-primary">Sign Up</button>
  </form>

  <div>

    <a href="?mode=signin">Sign In</a>
  </div>

  <% } %>
</div>



</body>
</html>
