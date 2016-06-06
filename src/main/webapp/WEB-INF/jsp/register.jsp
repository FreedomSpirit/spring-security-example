<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>New user registration</title>
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

		.hidden {
			display: none;
		}

		#wrapper {
			margin: auto;
			width: 400px;
			border: 1px solid #000;
			padding: 20px;
		}

		.high {
			color: #11AA11;
		}
		.medium {
			color: #1111AA;
		}
		.low {
			color: #AA1111;
		}
	</style>

	<script>
		function checkStrength() {
		  var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
			  document.getElementById("pwd").setAttribute("class", xhttp.responseText);
			}
		  }
		  xhttp.open("POST", "<c:url value='/password-str' />", true);
		  var params = "${_csrf.parameterName}=${_csrf.token}&password="
					+ document.getElementById("pwd-field").value;
		  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		  xhttp.setRequestHeader("Content-length", params.length);
		  xhttp.setRequestHeader("Connection", "close");
		  xhttp.send(params);
		}
	</script>
</head>

<body onload='document.loginForm.username.focus();'>

	<div id="wrapper">

		<h3>Create user</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>

		<form action="<c:url value='/register' />" method='post'>

			<table>
				<tr>
					<td>Username:</td>
					<td><div><input type='text' name='username'></div></td>
				</tr>
				<tr class='low' id='pwd'>
					<td>Password:</td>
					<td><div><input type='password' name='password' id='pwd-field' oninput='checkStrength()' /></div></td>
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