<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/shared/bootstrap.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patients list</title>
</head>
<body>
<%@include file="/WEB-INF/shared/navbar.jsp" %>




<div class="container-md mt-4">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form class="d-flex" action="${pageContext.request.contextPath}/patientslist" method="GET">

                <c:choose>
                <c:when test="${not empty param.search}">
                    <button class="btn btn-outline-danger" type="submit">Clean</button>
                </c:when>
                    <c:otherwise>
                        <input class="form-control me-2" type="search" name="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </c:otherwise>
                </c:choose>
            </form>
        </div>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Birth date</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>

        <c:if test="${patients != null}">
        <c:forEach items="${patients}" var="patient">
        <tr class="align-middle">
            <td>${patient.getLastName()}</td>
            <td>${patient.getFirstName()}</td>
            <td>${patient.getBirthDate()}</td>
            <td><img src="${patient.getImageUrl()}" alt="patient's profile picture" width="100"></td>
            <td><a href="${pageContext.request.contextPath}/patientdetails?id=${patient.id}" type="button" class="btn btn-secondary">Details</a></td>

        </tr>
        </tbody>
        </c:forEach>
        </c:if>
    </table>

    <div>
        <c:if test="${not empty sessionScope.user}">
            <td><a href="${pageContext.request.contextPath}/addapatient" type="button" class="btn btn-secondary">Add a patient</a></td>
        </c:if>
    </div>

</div>


</body>
</html>
