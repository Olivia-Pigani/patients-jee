<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="/WEB-INF/shared/bootstrap.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>Consultations</h2>

<table class="table">


    <thead>
    <tr>
        <th scope="col">Consultation's date</th>
        <th scope="col">Doctor's name</th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <c:if test="${consultations != null}">
    <c:forEach items="${consultations}" var="consultation">
    <tr>
        <th></th>
        <td>${consultation.dateConsultation}</td>
        <td>Doctor ${consultation.doctorFirstName} ${consultation.doctorLastName}</td>
        <td><a href="${pageContext.request.contextPath}/consultationdetails?id=${consultation.id}" type="button" class="btn btn-secondary">Details</a></td>

    </tr>
    </c:forEach>
    </c:if>
    </tbody>

</table>