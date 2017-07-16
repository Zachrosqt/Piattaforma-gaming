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

$("#reviewform").submit(function(e){
	
	if ($("#messagereview").val()==''){
		$('#alertmessagerew').html("<p style='color: red;'> Campo obbligatorio </p>");
	} else
		$('#alertmessagerew').html("");
	
	if ($("input[name=review-rate]").val()==''){
		$('#alertmessagerew').html("<p style='color: red;'> Valutazione obbligatoria </p>");
	} else
		$('#alertmessagerew').html("");
	
	if ($("#messagereview").val()!='' && $("input[name=review-rate]").val()!=''){
		var url = "game.op";
		$.ajax ({
			type: "POST",
			url: url,
			data: $("#reviewform").serialize(),
			
			success: function(data){
				if (data == "true") {
					sweetAlert("Ottimo!", "Recensione inviata correttamente", "success");
					$('#reviewBox').html("");
				} else {
					sweetAlert("Oops...", "Errore inserimento recensione!", "error");
				}
			},
			
			error: function(){
				sweetAlert("Oops...", "Errore comunicazione con il server!", "error");
			}
		});
		e.preventDefault();
	}
});


$("#modificaform").submit(function(e){
		
		if ($("#file").size()>1){
			$('#alertfile').html("<p style='color: red;'> Inserire immagine < 5MB</p>");
		} else
			$('#alertfile').html("");
	
		if ($("#inome").val()==''){
			$('#alertinome').html("<p style='color: red;'> Campo obbligatorio</p>");
		} else
			$('#alertinome').html("");
		
		if ($("#icognome").val()==''){
			$('#alerticognome').html("<p style='color: red;'> Campo obbligatorio</p>");
		} else
			$('#alerticognome').html("");
		
		if ($("#ieta").val()==''){
			$('#alertieta').html("<p style='color: red;'> Campo obbligatorio</p>");
		} else
			$('#alertieta').html("");
		
		if ($("#iemail").val()==''){
			$('#alertiemail').html("<p style='color: red;'> Campo obbligatorio</p>");
		} else
			$('#alertiemail').html("");
		
		if ($("#ipassword").val()==''){
			$('#alertipassword').html("<p style='color: red;'> Campo obbligatorio</p>");
		} else
			$('#alertipassword').html("");
		
		if ($("#file").size()<=5000000 && $("#inome").val()!='' && $("#icognome").val()!='' && $("#ieta").val()!='' && $("#iemail").val()!='' && $("#ipassword").val()!=''){
			
		
		var url = "editprofile.op";
		
		var user = $("#iusername").val();
		
		var form = $('#modificaform')[0];

		var data = new FormData(form);
		$.ajax ({
			type: "POST",
			enctype: 'multipart/form-data',
			processData: false,  // Important!
	        contentType: false,
			url: url,
			data: data,
			
			success: function(data){
				if (data == "true") {
					swal({
						  title: "Ottimo!",
						  text: "Profilo modificato correttamente!",
						  type: "success",
						  closeOnConfirm: false}, 
						  function(){
							  $(window.location).attr("href", "userprofile.op?user=" + user);
						});
				} else if (data == "false form") {
					sweetAlert("Oops...", "Errore comunicazione dati!", "error");
				} else if (data == "false file") {
					sweetAlert("Oops...", "File selezionato incorretto! Inserire un file di tipo png", "error");
				} else if (data == "false size") {
					sweetAlert("Oops...", "Sembra che il tuo file sia di dimensioni superiori a 5Mb", "error");
				} else if (data == "false email") {
					sweetAlert("Oops...", "E-Mail inserita gia' in uso", "error");
				} else {
					sweetAlert("Oops...", "Errore modifica profilo!", "error");
				}
			},
			
			error: function(){
				sweetAlert("Oops...", "Errore comunicazione con il server!", "error");
			}
		});
		e.preventDefault();
		}
});


