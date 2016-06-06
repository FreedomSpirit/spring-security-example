<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Test App</title>
	<style type="text/css">
		.hidden {
			display: none;
		}

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
		<h1>Hello world!</h1>

		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<div class="hidden">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</div>
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
	</div>

</body>
</html>