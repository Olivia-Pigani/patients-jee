<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Formular</title>
</head>
<body>

<div class="container-fluid">

    <h2>Add A Patient</h2>

    <form>
        <div class="form-group row">
            <label for="inputEmail3" class="col-sm-2 col-form-label">First Name</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputEmail3" placeholder="Bobby">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPassword3" class="col-sm-2 col-form-label">Last Name</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPassword3" placeholder="Brown">
            </div>
        </div>
        <fieldset class="form-group">
            <div class="row">
                <legend class="col-form-label col-sm-2 pt-0">Birth Date</legend>
                <input type="date" >

            </div>
        </fieldset>
        <div class="form-group row">
            <div class="col-sm-10">
                <div class="form-group">
                    <label for="exampleFormControlFile1">Image file</label>
                    <input type="file" class="form-control-file" id="exampleFormControlFile1">
                </div>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Sign in</button>
            </div>
        </div>


        <div>

            <a href="${pageContext.request.contextPath}/addapatient" type="button" class="btn btn-primary">Add</a>
            <a href="${pageContext.request.contextPath}/updateapatient" type="button" class="btn btn-warning">Update</a>
            <a href="${pageContext.request.contextPath}/deleteapatient" type="button" class="btn btn-danger">Delete</a>

        </div>


    </form>











</div>

</body>
</html>
