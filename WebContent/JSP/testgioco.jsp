<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Test Game</title>
	
	<link rel="stylesheet" type="text/css" href="assets/css/custom.css" />
</head>
<body id="bodyGame">
	<div id="outPopUp">
		<h1>${gioco}</h1>
		<div id="lv"><h3 style='color: red;'> Livello: </h3></div>
		<div id="lvGlobale"><h3 style='color: red;'> Lv globale: </h3></div>
		<div id="exp"><p> Exp in gioco: </p></div>
		<div id="accessi"><p> Numero Accessi: </p></div>
		 <button type="button" id="testpoint">Add Point!</button> 
		 <button type="button" id="testclose">Close Game!</button> 
	</div>
 
<!-- jQuery -->
<script type="text/javascript" src="assets/bower_components/jquery/dist/jquery.min.js"></script>

<script type="text/javascript">
$("#testpoint").click(function(e){
	var url = "Soulcalibur.op";
	$.ajax ({
		type: "POST",
		url: url,
		data: {button: "exp", user: "${utenteGameplatform.username}"},
		
		success: function(data){
			var res = data.split(",");
			$('#lv').html("<h3 style='color: red;'> Livello: " + res[0] + "</h3>");
			$('#exp').html("<p> Exp in gioco: " + res[1] + "</p>");
			$('#lvGlobale').html("<h3 style='color: red;'> Lv globale: " + res[2] + "</h3>");
			$('#accessi').html("<p> Numero Accessi: " + res[3] + "</p>");
		}
	});
	e.preventDefault();
});

$("#testclose").click(function(e){
	var url = "Soulcalibur.op";
	$.ajax ({
		type: "POST",
		url: url,
		data: {button: "close", user: "${utenteGameplatform.username}"},
		
		success: function(data){
			var res = data.split(",");
			$('#lv').html("<h3 style='color: red;'> Livello: " + res[0] + "</h3>");
			$('#exp').html("<p> Exp in gioco: " + res[1] + "</p>");
			$('#lvGlobale').html("<h3 style='color: red;'> Lv globale: " + res[2] + "</h3>");
			$('#accessi').html("<p> Numero Accessi: " + res[3] + "</p>");
			close();
		}
	});
	e.preventDefault();
});

</script>

</body>
</html>