<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../shared/bootstrap.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultation's details</title>
</head>
<body>

<div class="container-fluid">

    <h2>Consultation dating from ${consultation.dateConsultation}</h2>
    <div>
        <h3>Patient</h3>
        <p> First Name : ${patient.firstName}</p>
        <p> Last Name : ${patient.lastName}</p>
        <p> Birth Date : ${patient.birthDate}</p>

    </div>
    <div>
        <h4>Doctor : ${consultation.doctorFirstName} ${consultation.doctorLastName}</h4>
    </div>

    <div>
        <c:if test="${medicalForms != null}">
        <c:forEach items="${medicalForms}" var="medicalForm">
        <h3>Medical Form</h3>
        <p>Care Type : ${medicalForm.careType}</p>
        <p>Duration : ${medicalForm.duration} days</p>
        </c:forEach>
        </c:if>


    </div>
   <div>
<c:if test="${prescriptions != null}">
    <c:forEach items="${prescriptions}" var="prescription">
        <h3>Prescription</h3>
        <p>Pills type : ${prescription.pillType}</p>
        <p>Duration : ${prescription.duration} days</p>

    </c:forEach>
</c:if>
    </div>

    <div>



    </div>

</div>


</body>
</html>
