<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Welcome to Spring Social ORCID 
</h1>

	<form action="<c:url value="/signin/orcid" />" method="POST">
		<button type="submit">Sign in with ORCID</button>
		<input type="hidden" name="scope"
			value="/authenticate" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
	</form>
	
</body>
</html>