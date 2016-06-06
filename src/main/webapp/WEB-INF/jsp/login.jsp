<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Login Page</title>
	<style type="text/css">
		.error {
			padding: 15px;
			margin-bottom: 20px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #a94442;
			background-color: #f2dede;
			border-color: #ebccd1;
		}

		.msg {
			padding: 15px;
			margin-bottom: 20px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #31708f;
			background-color: #d9edf7;
			border-color: #bce8f1;
		}

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
<body onload="document.loginForm.username.focus();">

	<div id="wrapper">

		<h3>Login with Username and Password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form action="<c:url value='/login' />" method="post">


			<table>
				<tr>
					<td>User:</td>
					<td><div><input type="text" name="username" /></div></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><div><input type="password" name="password" /></div></td>
				</tr>
				<tr>
					<td colspan='2'><div><input name="submit" type="submit"
						value="submit" /></div></td>
				</tr>
			</table>

			<div class="hidden"><input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /></div>


		</form>
	</div>

</body>
</html>