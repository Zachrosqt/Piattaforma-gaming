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
				$(window.location).attr("href", "gameplatform.op");
			}
			else {
				sweetAlert("Oops...", "Username o password non corretti!", "error");
			}
		},
		
		error: function(){
			sweetAlert("Oops...", "Errore comunicazione con il server!", "error");
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
					swal({
						  title: "Ottimo!",
						  text: "La tua registrazione e' andata a buon fine!",
						  type: "success",
						  closeOnConfirm: false}, 
						  function(){
							  $(window.location).attr("href", "login.op");
						});
				} else if (data == "false username"){
					sweetAlert("Oops...", "Username gia' in uso!", "error");
				} else if (data == "false mail"){
					sweetAlert("Oops...", "E-Mail gia' in uso!", "error");
				} else {
					sweetAlert("Oops...", "Errore in fase di registrazione!", "error");
				}
			},
			error: function(){
				sweetAlert("Oops...", "Errore comunicazione con il server!", "error");
			}
		});
		e.preventDefault();
	}
});

$('#logout').click(function(){
	var url = "gameplatform.op";
	$.ajax ({
		type: "POST",
		url: url,
		
		success: function(data){
			$(window.location).attr("href", "login.op");
		},
		
	});
	e.preventDefault();
});

$("#contactform").submit(function(e){
	
	if ($("#message").val()==''){
		$('#alert').html("");
		$('#alertmessage').html("<p style='color: red;'> Messaggio obbligatorio </p>");
	} else
		$('#alertmessage').html("");
	
	if ($("#message").val()!=''){
		var url = "contact.op";
		$.ajax ({
			type: "POST",
			url: url,
			data: $("#contactform").serialize(),
			
			success: function(data){
				if (data == "true") {
					sweetAlert("Ottimo!", "Messaggio inviato correttamente", "success");
				} else {
					sweetAlert("Oops...", "Errore invio messaggio!", "error");
				}
			},
			
			error: function(){
				sweetAlert("Oops...", "Errore comunicazione con il server!", "error");
			}
		});
		e.preventDefault();
	}
});


