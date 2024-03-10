<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/shared/bootstrap.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid mb-5 ms-2 ">

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

        <td><fmt:formatDate value="${consultation.dateConsultation}" pattern="yyyy-MM-dd" /></td>
        <td>Doctor ${consultation.doctorFirstName} ${consultation.doctorLastName}</td>
        <td><a href="${pageContext.request.contextPath}/consultationdetails?id=${consultation.id}" type="button" class="btn btn-secondary">Details</a></td>

    </tr>
    </c:forEach>
    </c:if>
    </tbody>

</table>

</div>

