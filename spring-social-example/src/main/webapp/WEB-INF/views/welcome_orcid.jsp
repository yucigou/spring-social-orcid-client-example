<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome</title>
</head>
<body>
	<h1>
		Welcome to Spring Social ORCID
	</h1>

	<h2>
		Congratulations ${name}!
	</h2>
	
	<p>You have singed in with ORCID and your ORCID ID is ${orcidId}</p>

</body>
</html>