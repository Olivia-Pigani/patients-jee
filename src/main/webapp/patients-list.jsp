<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/shared/bootstrap.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patients list</title>
</head>
<body>
<%@include file="/WEB-INF/shared/navbar.jsp" %>

<div class="container-md ">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Birth date</th>
            <th></th>
        </tr>
        </thead>
        <tbody>

        <c:if test="${patients != null}">
        <c:forEach items="${patients}" var="patient">
        <tr>
            <th></th>
            <td>${patient.getLastName()}</td>
            <td>${patient.getFirstName()}</td>
            <td>${patient.getBirthDate()}</td>
            <td>${patient.getImageUrl()}</td>
        </tr>
        </tbody>
        </c:forEach>
        </c:if>
    </table>


</div>


</body>
</html>
