<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../shared/bootstrap.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Formular</title>
</head>
<body>


<%@include file="/WEB-INF/shared/navbar.jsp" %>


<main class="container mt-4 ">
    <div class="row justify-content-center">
        <div class="col-md-8 offset-2">
    <h2 class="my-4 ">Add A Patient</h2>

    <form action="addapatient" method="post">
        <div class="form-group row mb-3 ">
            <label for="firstName" class="col-sm-2 col-form-label">First Name</label>
            <div class="col-sm-10">
                <div class="col-6">
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Bob" required>
                </div>

            </div>
        </div>
        <div class="form-group row mb-3">
            <label for="lastName" class="col-sm-2 col-form-label">Last Name</label>
            <div class="col-sm-10">
                <div class="col-6">
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Brown" required>
                </div>
            </div>
            <fieldset class="form-group mb-3">
                <div class="row mt-3">
                    <label for="birthDate" class="col-sm-2 col-form-label">Birth Date</label>
                    <div class="col-sm-10">
                        <input type="date" id="birthDate" name="birthDate" required>
                    </div>
                </div>
            </fieldset>

            <div>

                <button type="submit" class="btn btn-primary">Submit</button>

            </div>
        </div>


    </form>
        </div>
        </div>
</main>

</body>
</html>
