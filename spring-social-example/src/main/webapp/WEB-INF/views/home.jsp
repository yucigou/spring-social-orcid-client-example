<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
</head>
<body>
	<h1>
		Welcome to Spring Social ORCID...
	</h1>

	<form id="target" action="<c:url value="/signin/orcid" />" method="POST">
		<button type="submit">Sign in with ORCID</button>
		<input type="hidden" name="scope" id="orcid_scope" value="/read-limited /orcid-works/create" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
		<input type="checkbox" name="additional_permission" value="/read-limited /orcid-works/create" checked> Allow for (1) Reading Entire Record and (2) Adding a Research Activity
		<input type="checkbox" name="remember-me" value="true" checked /> Remember Me
	</form>
	
	<form action="<c:url value="/signin/facebook" />" method="POST" style="display:none">
		<button type="submit">Sign in with Facebook</button>
		<input type="hidden" name="scope"
			value="public_profile, user_friends, user_actions.video" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> 
	</form>

	<form name='loginForm' action="<c:url value='/auth/login_check' />"	method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username'></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>

			<tr>
				<td></td>
				<td>Remember Me: <input type="checkbox" name="remember-me" /></td>
			</tr>

			<tr>
				<td colspan='2'><input name="submit" type="submit" value="submit" /></td>
			</tr>

		</table>

		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

</body>
<script type="text/javascript">
$(document).ready(function() {
    $('input[name="additional_permission"]').change(function() {
        if($(this).is(":checked")) {
            $('#orcid_scope').val($('input[name="additional_permission"]').val());
        } else {
        	$('#orcid_scope').val("/authenticate");
        }
    });
    
    // $( "#target" ).submit();
});
</script>
</html>