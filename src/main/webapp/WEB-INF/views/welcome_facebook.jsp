<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome</title>
</head>
<body>
<h1>
	Welcome to Spring Social Facebook ${name}!
</h1>
<img src="${imgUrl}" alt="My Profile Image">

	<h2>Your Movies:</h2>
	<c:forEach items="${movies}" var="movie">
		<p>${movie}</p>
	</c:forEach>

	<h2>
		You have ${numFriends} friends!
	</h2>

	<form action="<c:url value="/connect/facebook" />" method="post">
	  <div class="formInfo">
	    <p>
	      Spring Social Client Example is connected to your Facebook account.
	      Click the button if you wish to disconnect.
	    </p>
	  </div>
	  <button type="submit">Disconnect</button>	
	  <input type="hidden" name="_method" value="delete" />
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	
</body>
</html>