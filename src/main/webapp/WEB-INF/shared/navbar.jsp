<%@include file="bootstrap.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <div >
        <a class="navbar-brand" href="${pageContext.request.contextPath}/patientslist">App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        </div>

        <div>
            <c:choose>
            <c:when test="${not empty sessionScope.user}">
            <a href="${pageContext.request.contextPath}/signform?action=signout" class="btn btn-outline-success" type="submit">Sign out</a>
            </c:when>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/signform" class="btn btn-outline-success" type="submit">Sign in</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

