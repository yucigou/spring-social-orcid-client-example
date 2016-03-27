<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Welcome</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
</head>
<body>
	<h1>
		Welcome to Spring Social ORCID
	</h1>

	<h2>
		Congratulations ${name}!
	</h2>
	
	<p>You have singed in with ORCID and your ORCID ID is ${orcidId}</p>

	<form action="<c:url value="/connect/orcid" />" method="post" id="form">
	  <div class="formInfo">
	    <p>
	      Spring Social Client Example is connected to your ORCID account.
	      Click the button if you wish to disconnect.
	    </p>
	  </div>
	  <button id="logout" type="submit">Disconnect</button>	
	  <input type="hidden" name="_method" value="delete" />
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>

<script type="text/javascript">
$(document).ready(function () { 
    $('#form').submit( function(event) {
        var formId = this.id, form = this;
        
        $.ajax({
    		url: "https://sandbox.orcid.org/userStatus.json?logUserOut=true",
    		jsonp: "callback",
    		dataType: "jsonp",
    		success: function() {
    			console.info("Logged out from ORCID.");
    		}
    	});

        event.preventDefault();

        setTimeout( function () { 
            form.submit();
        }, 1000);
    }); 
});
</script>
</body>
</html>