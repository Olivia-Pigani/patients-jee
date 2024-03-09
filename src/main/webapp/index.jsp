<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/shared/bootstrap.jsp" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Menu</title>
</head>
<body>
<main class="container mt-5">
  <div class="row">
    <div class="col-12 text-center">
    <h1>Welcome to patient jakarta !</h1>
    </div>
  </div>

  <div class="row justify-content-center mt-3 ">
    <div class="col text-center">
<img src="https://images.unsplash.com/photo-1576091160550-2173dba999ef?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" width="800" height="500" alt="a woman that write on a laptop">
    </div>
  </div>

  <div class="row justify-content-center mt-5">
    <div class="col-auto">
<a href="${pageContext.request.contextPath}/patientslist" type="button" class="btn btn-info">Main page</a>
    </div>
  </div>
</main>
</body>
</html>