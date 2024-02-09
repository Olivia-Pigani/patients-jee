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

    <div>

        <h2>Consultations</h2>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Date</th>
                <th scope="col">Doctor's name</th>
                <th></th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <th></th>
                <td>${consultation.dateConsultation}</td>
                <td> Dr. ${consultation.doctorFirstName} ${consultation.doctorLastName}</td>
                <td><a href="${pageContext.request.contextPath}/consulationdetails?id=${consultation.id}" type="button" class="btn btn-secondary">Details</a></td>

            </tr>
            </tbody>
        </table>


    </div>



</div>



</body>
</html>
