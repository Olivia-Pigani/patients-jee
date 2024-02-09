<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../shared/bootstrap.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient's details</title>
</head>
<body>
<%@include file="../shared/navbar.jsp"%>

<div class="container-fluid mt-3 justify-content-center" >



    <div class="card mb-3" style="max-width: 540px;">
        <div class="row g-0">
            <div class="col-md-3">
                <img src=${patient.imageUrl} class="img-fluid rounded-start" alt="patient's picture" >
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">${patient.firstName} ${patient.lastName}</h5>
                    <p class="card-text">birth date : ${patient.birthDate}</p>
                </div>
            </div>
        </div>
    </div>




</div>

<div>

    <%@include file="../../consultations-list.jsp"%>

</div>


</body>
</html>
