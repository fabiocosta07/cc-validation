<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Create an account</title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
  </head>

  <body>

    <div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2> <a href="/ccs">Search Credit Cards</a> | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
    </c:if>
    
        <form:form method="POST" modelAttribute="ccForm" class="form-signin" action="/ccs">
            <h2 class="form-signin-heading">Add Credit Card</h2>
            <spring:bind path="number">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="number" class="form-control" placeholder="Number"
                                autofocus="true"></form:input>
                    <form:errors path="number"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="expiryDate">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="expiryDate" class="form-control" placeholder="Expiry Date"></form:input>
                    <form:errors path="expiryDate"></form:errors>
                </div>
            </spring:bind>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>

    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
  </body>
</html>
