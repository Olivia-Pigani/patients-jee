<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../shared/bootstrap.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultation's formular</title>
</head>
<body>


<main class="container">

    <h2>Consultation's formular</h2>

<form action="add-consultation" method="POST">

    <input type="hidden" name="patientId" value="${patient.id}">


    <div class="mb-3">
        <h4>Doctor</h4>
        <input type="text" class="form-control" id="doctorFirstName" name="doctorFirstName" value="${consultation.doctorFirstName}"  placeholder="Bob" required>
        <input type="text" class="form-control" id="doctorLastName" name="doctorLastName" value="${consultation.doctorLastName}"  placeholder="Wallace" required>
    </div>
    <div class="mb-3">
        <label for="dateConsultation" class="form-label">Consultation's date</label>
        <input type="date" class="form-control" id="dateConsultation" name="dateConsultation" value="${consultation.dateConsultation}" >
        <span>By default the date will be the same as the submit button validation</span>

    </div>

    <div>
        <h3>Medical Form</h3>

        <div>
            <label for="careType" class="form-label">Care type</label>
            <input type="text" class="form-control" id="careType" name="careType" value="${mF.careType}" required>
        </div>

        <div>

            <label for="treatment-duration" class="form-label">Duration</label>
            <input type="number" class="form-control" id="treatment-duration" name="treatment-duration" value="${mF.duration}" required>
            <select class="form-select" aria-label="Default select example">
                <option selected>days</option>
                <option value="days">days</option>
                <option value="months">months</option>
                <option value="years">years</option>
            </select>


        </div>


    </div>


    <div>

        <h3>Prescription</h3>

        <div>

            <label for="pillType" class="form-label">pills type</label>
            <input type="text" class="form-control" id="pillType" name="pillType" value="${prescription.pillType}" required>

        </div>



        <div>

            <label for="prescription-duration" class="form-label">Duration</label>
            <input type="number" class="form-control" id="prescription-duration" name="prescription-duration" value="${prescription.duration}" required>
            <select class="form-select" aria-label="Default select example">
                <option selected>days</option>
                <option value="days">days</option>
                <option value="months">months</option>
                <option value="years">years</option>
            </select>


        </div>



    </div>

    <div class="mt-4">
        <a href="${pageContext.request.contextPath}/patientdetails?id=${patient.id}" class="btn btn-secondary">Back to Patient details</a>
        <button type="submit" class="btn btn-primary">Submit</button>

    </div>


</form>

</main>










</body>
</html>
