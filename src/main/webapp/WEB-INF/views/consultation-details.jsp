<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../shared/bootstrap.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultation's details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.0/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-4 ">

    <h2 class="mb-4">Consultation dating from ${consultation.dateConsultation}</h2>

    <div class="card mb-3">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="${patient.imageUrl}" class="img-fluid rounded-start" alt="patient's image"  width="250">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h3 class="card-title">Patient Details</h3>
                    <p class="card-text">First Name: ${patient.firstName}</p>
                    <p class="card-text">Last Name: ${patient.lastName}</p>
                    <p class="card-text">Birth Date: ${patient.birthDate}</p>
                </div>
            </div>
        </div>
    </div>

    <div class="card mb-3">
        <div class="card-body">
            <h4 class="card-title">Doctor</h4>
            <p class="card-text">${consultation.doctorFirstName} ${consultation.doctorLastName}</p>
        </div>
    </div>

    <c:if test="${medicalForms != null}">
        <div class="card mb-3">
            <div class="card-body">
                <h3 class="card-title">Medical Form</h3>
                <c:forEach items="${medicalForms}" var="medicalForm">
                    <p class="card-text">Care Type: ${medicalForm.careType}</p>
                    <p class="card-text">Duration: ${medicalForm.duration} days</p>
                </c:forEach>
            </div>
        </div>
    </c:if>

    <c:if test="${prescriptions != null}">
        <div class="card mb-3">
            <div class="card-body">
                <h3 class="card-title">Prescription</h3>
                <c:forEach items="${prescriptions}" var="prescription">
                    <p class="card-text">Pills type: ${prescription.pillType}</p>
                    <p class="card-text">Duration: ${prescription.duration} days</p>
                </c:forEach>
            </div>
        </div>
    </c:if>

    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/patientslist" class="btn btn-secondary">Back to Patients</a>


        <a href="${pageContext.request.contextPath}/patientslist" class="btn btn-secondary">Update</a>

<%--         put delete action in a form of post type avoid web preloading and '< a >' automatic deletion--%>
        <form action="${pageContext.request.contextPath}/delete-consultation" method="post">
            <input type="hidden" name="consultationId" value="${consultation.id}" />
            <input type="hidden" name="patientId" value="${patient.id}" />
            <button type="submit" class="btn btn-danger">Delete consultation</button>
        </form>

    </div>

</div>

</body>
</html>