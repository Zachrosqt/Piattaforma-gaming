$("#loginform").submit(function(e){
	if ($("#username").val()== ''){
		$('#alert').html("");
		$('#alertusername').html("<p style='color: red;'> Username obbligatoria</p>");
	} else
		$('#alertusername').html(""); 
	if ($("#password").val()==''){
		$('#alert').html("");
		$('#alertpassword').html("<p style='color: red;'> Password obbligatoria </p>");
	} else
		$('#alertpassword').html("");
	if ($("#username").val()!='' && $("#password").val()!=''){
	var url = "login.op";
	$.ajax ({
		type: "POST",
		url: url,
		data: $("#loginform").serialize(),
		
		success: function(data){
			if (data == "true") {
				$('#alert').html("");
				$(window.location).attr("href", "gameplatform.op");
			}
			else {
				$('#alert').html("<p style='color: red;'> Username o password non corretti </p>");
			}
		},
		
		error: function(){
			$('#alert').html("<p style='color: red;'> Errore comunicazione con il server </p>");
		}
	});
	e.preventDefault();
	}
});

$("#registrationform").submit(function(e){
	
	if ($("#username").val()== ''){
		$('#alert').html("");
		$('#alertusername').html("<p style='color: red;'> Username obbligatoria</p>");
	} else
		$('#alertusername').html(""); 
	
	if ($("#password").val()==''){
		$('#alert').html("");
		$('#alertpassword').html("<p style='color: red;'> Password obbligatoria </p>");
	} else
		$('#alertpassword').html("");
	
	if ($("#confirmpassword").val()==''){
		$('#alert').html("");
		$('#alertconfirm').html("<p style='color: red;'> Conferma password obbligatoria </p>");
	}
	else if ($('#password').val() != $('#confirmpassword').val()) {
		$('#alert').html("");
		$('#alertconfirm').html("<p style='color: red;'> Password non corrispondenti </p>");
	}
	else
		$('#alertconfirm').html("");
	
	if ($("#eta").val()== ''){
		$('#alert').html("");
		$('#alerteta').html("<p style='color: red;'> Eta' obbligatoria</p>");
	} else
		$('#alerteta').html(""); 
	
	if ($("#nome").val()== ''){
		$('#alert').html("");
		$('#alertname').html("<p style='color: red;'> Nome obbligatorio</p>");
	} else
		$('#alertname').html(""); 
	
	if ($("#cognome").val()== ''){
		$('#alert').html("");
		$('#alertsurname').html("<p style='color: red;'> Cognome obbligatorio</p>");
	} else
		$('#alertsurname').html(""); 
	
	if ($("#mail").val()== ''){
		$('#alert').html("");
		$('#alertemail').html("<p style='color: red;'> email obbligatoria</p>");
	} else
		$('#alertemail').html(""); 
	
	if ($("#confirmmail").val()== ''){
		$('#alert').html("");
		$('#alertconfirmemail').html("<p style='color: red;'> Conferma email obbligatoria</p>");
	}
	else if ($('#mail').val() != $('#confirmmail').val()) {
		$('#alert').html("");
		$('#alertconfirmemail').html("<p style='color: red;'> E-mail non corrispondenti </p>");
	}
	else
		$('#alertconfirmemail').html(""); 
	
	if ($("#username").val()!='' && $("#password").val()!='' && ($("#confirmpassword").val()!='' && $("#confirmpassword").val() == $("#password").val()) && $("#eta").val()!='' && $("#mail").val()!='' && ($("#confirmmail").val()!='' && $("#confirmmail").val() == $("#mail").val()) && $("#cognome").val()!='' && $("#nome").val()!=''){
		var url = "registration.op";
		$.ajax ({
			type: "POST",
			url: url,
			data: $("#registrationform").serialize(),
			
			success: function(data){
				if (data == "true") {
					$('#alert').html("");
					$(window.location).attr("href", "login.op");
				} else if (data == "false username"){
					$('#alert').html("<p style='color: red;'> Username gi&agrave in uso </p>");
				} else if (data == "false mail"){
					$('#alert').html("<p style='color: red;'> E-Mail gi&agrave in uso </p>");
				} else {
					$('#alert').html("<p style='color: red;'> Errore in fase di registrazione </p>");
				}
			},
			error: function(){
				$('#alert').html("<p style='color: red;'> Errore comunicazione con il server </p>");
			}
		});
		e.preventDefault();
	}
});

