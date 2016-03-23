<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Welcome!  
</h1>

	<form action="<c:url value="/signin/facebook" />" method="POST">
		<button type="submit">Sign in with Facebook</button>
		<input type="hidden" name="scope"
			value="public_profile, user_friends, user_actions.video" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
	</form>

	<form action="<c:url value="/signin/orcid" />" method="POST">
		<button type="submit">Sign in with ORCID</button>
		<input type="hidden" name="scope"
			value="/orcid-profile/read-limited" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
	</form>
	
</body>
</html>