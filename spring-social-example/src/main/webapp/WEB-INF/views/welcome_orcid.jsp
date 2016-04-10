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
		Congratulations <span id="user_name">${name}</span>!
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
	
	<a id="linkOpener" href="javascript:void(0)">Back to your application</a>

<script type="text/javascript">
var isComplete = false;
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
    
    $('#linkOpener').click(function(){
    	console.log("User name: " + $('#user_name').text());
    	// window.opener.loadClaiming($('#user_name').text());
    	window.opener.postMessage("Done", "http://localhost:9080");
    	// isComplete = true;
    	window.close();
    });  
    
    window.opener.postMessage("Done", "http://localhost:9080");
    window.close();
});

function receiveMessage(event) {
	console.log("Msg received.");
	if (isComplete) {
		event.source.postMessage("Done", "http://localhost:9080");
		window.close();
	}
	console.log("Not complete yet");
}
window.addEventListener("message", receiveMessage, false);

function testX() {
	$.getJSON("http://localhost:8080/springsocial/api/orcid/profile", function(result) {
		console.log("My profile: ");
      	console.log(result);
  });
}
</script>
</body>
</html>