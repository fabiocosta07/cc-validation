<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>
<body>

	<div class="container">
	
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2> <a href="/ccs/add">Add Credit Card</a> | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
    	
		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>

        <form:form method="POST" modelAttribute="ccSearchForm" class="form-signin" action="/ccsearch">
            <h2 class="form-signin-heading">Search Credit Card</h2>
            <spring:bind path="number">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="number" class="form-control" placeholder="Number"
                                autofocus="true"></form:input>
                    <form:errors path="number"></form:errors>
                </div>
            </spring:bind>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>
        <table class="table table-striped">
			<thead>
				<tr>
					<th>Number</th>
					<th>Name</th>
					<th>Expire Date</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="cc" items="${ccs}">
			    <tr>
				<td>${cc.number}</td>
				<td>${cc.name}</td>
				<td><fmt:formatDate value="${cc.expireDate}" pattern="MM/yyyy" /></td>
				<td>
				  <spring:url value="/ccs/${cc.id}/delete" var="deleteUrl" />
                  <a href="${deleteUrl}">Delete</a>
			    </tr>
			</c:forEach>
		</table>

	</div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>