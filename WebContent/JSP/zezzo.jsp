<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zezzo Game</title>
</head>
<body>
<div id="lv"></div>
<div id="lvGlobale"></div>
<div id="exp"></div>
<div id="accessi"></div>
 <button type="button" id="zezzopoint">Add Point!</button> 
 <button type="button" id="zezzoclose">Close Game!</button> 
 
<!-- jQuery -->
<script type="text/javascript" src="assets/bower_components/jquery/dist/jquery.min.js"></script>

<script type="text/javascript">
$("#zezzopoint").click(function(e){
	var url = "Zezzo.op";
	$.ajax ({
		type: "POST",
		url: url,
		data: {button: "exp", user: "Er Zezzo"},
		
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

$("#zezzoclose").click(function(e){
	var url = "Zezzo.op";
	$.ajax ({
		type: "POST",
		url: url,
		data: {button: "close", user: "Er Zezzo"},
		
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

</script>

</body>
</html>