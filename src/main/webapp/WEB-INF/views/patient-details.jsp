<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../shared/bootstrap.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient's details</title>
</head>
<body>
<%@include file="../shared/navbar.jsp"%>

<div class="container-fluid mt-3 " >



    <div class="card mb-3" style="max-width: 540px;">
        <div class="row g-0">
            <div class="col-md-3">
                <img src=${patient.imageUrl} class="img-fluid rounded-start" alt="patient's picture" >
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">${patient.firstName} ${patient.lastName}</h5>
                    <p class="card-text"> <fmt:formatDate value="${patient.birthDate}" pattern="yyyy-MM-dd" /></p>
                </div>
            </div>
        </div>
    </div>




</div>

<div>

    <jsp:include page="consultations-list.jsp" />

</div>

<div>
    <td><a href="${pageContext.request.contextPath}/add-consultation?patientId=${patient.id}" type="button" class="btn btn-secondary">Add a consultation</a></td>

    <%--         use form of post type avoid web preloading and '< a >' automatic deletion--%>
    <form action="${pageContext.request.contextPath}/delete-patient" method="post">
        <input type="hidden" name="patientId" value="${patient.id}" />
        <button type="submit" class="btn btn-danger">Delete patient</button>
    </form>


</div>


</body>
</html>
