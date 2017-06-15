$("#loginform").submit(function(e){
	if ($("#username").val()== ''){
		$('#alertusername').html("<p style='color: red;'> Username obbligatoria</p>");
	} else
		$('#alertusername').html(""); 
	if ($("#password").val()==''){
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
		$('#alertusername').html("<p style='color: red;'> Username obbligatoria</p>");
	} else
		$('#alertusername').html(""); 
	
	if ($("#password").val()==''){
		$('#alertpassword').html("<p style='color: red;'> Password obbligatoria </p>");
	} else
		$('#alertpassword').html("");
	
	if ($("#confirmpassword").val()==''){
		$('#alertconfirm').html("<p style='color: red;'> Conferma password obbligatoria </p>");
	}
	else if ($('#password').val() != $('#confirmpassword').val()) {
		$('#alertconfirm').html("<p style='color: red;'> Password non corrispondenti </p>");
	}
	else
		$('#alertconfirm').html("");
	
	if ($("#eta").val()== ''){
		$('#alerteta').html("<p style='color: red;'> Eta' obbligatoria</p>");
	} else
		$('#alerteta').html(""); 
	
	if ($("#nome").val()== ''){
		$('#alertname').html("<p style='color: red;'> Nome obbligatorio</p>");
	} else
		$('#alertname').html(""); 
	
	if ($("#cognome").val()== ''){
		$('#alertsurname').html("<p style='color: red;'> Cognome obbligatorio</p>");
	} else
		$('#alertsurname').html(""); 
	
	if ($("#mail").val()== ''){
		$('#alertemail').html("<p style='color: red;'> email obbligatoria</p>");
	} else
		$('#alertemail').html(""); 
	
	if ($("#confirmmail").val()== ''){
		$('#alertconfirmemail').html("<p style='color: red;'> Conferma email obbligatoria</p>");
	}
	else if ($('#mail').val() != $('#confirmmail').val()) {
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
					$(window.location).attr("href", "gameplatform.op");
				}
				else {
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

