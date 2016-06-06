<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Test App</title>
	<style type="text/css">
		#wrapper {
			margin: auto;
			width: 400px;
			border: 1px solid #000;
			padding: 20px;
		}
	</style>
</head>
<body>
	<div id="wrapper">
		<h3>Hello world page: <a href='<c:url value="/hello" />'><c:url value='/hello' /></a></h3>
		<sec:authorize access="!hasRole('ROLE_USER')">
			<h3>Please log in here: <a href='<c:url value="/login" />'><c:url value='/login' /></a></h3>
			<h3>Or register yourself: <a href='<c:url value="/register" />'><c:url value='/register' /></a></h3>
		</sec:authorize>

		<sec:authorize access="hasRole('ROLE_USER')">
			<c:url value="/logout" var="logoutUrl" />
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<script>
				function formSubmit() {
					document.getElementById("logoutForm").submit();
				}
			</script>

			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<h3>User : ${pageContext.request.userPrincipal.name} | <a
						href="javascript:formSubmit()"> Logout</a>
				</h3>
			</c:if>
		</sec:authorize>
	</div>
</body>
</html>