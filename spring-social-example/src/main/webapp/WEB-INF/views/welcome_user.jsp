<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome</title>
</head>
<body>
<h1>
	Welcome ${name}!
</h1>
<img src="${imgUrl}" alt="My Profile Image">

	<h2>Your Movies:</h2>
	<c:forEach items="${movies}" var="movie">
		<p>${movie}</p>
	</c:forEach>

	<h2>
		You have ${numFriends} friends!
	</h2>

</body>
</html>