<%@include file="bootstrap.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<nav class="navbar navbar-expand-lg bg-dark navbar-dark">
    <div class="container-fluid">
        <div >
        <a class="navbar-brand" href="${pageContext.request.contextPath}/patientslist">App</a>

        </div>

        <div>
            <c:choose>
            <c:when test="${not empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/signform?action=signout" class="btn btn-secondary" type="submit">Sign out</a>
            </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/signform" class="btn btn-info" type="submit">Sign in</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

